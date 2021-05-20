package com.j2ee.blog.Handler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

//@ControllerAdvice，是Spring3.2提供的新注解,它是一个Controller增强器,可对controller中被 @RequestMapping注解的方法加一些逻辑处理。
//最常用的就是异常处理

// 需要配合@ExceptionHandler使用。
// 当将异常抛到controller时,可以对异常进行统一处理,规定返回的json格式或是跳转到一个错误页面

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    //括号里加上 Exception.class
    //只要是Exception级别的错误都能拦截
    @ExceptionHandler(Exception.class)
    //可以从request获取访问路径的url

    //ModelAndView中有一个view值和一个model值。
    //view是我们条转到的页面的地址，model则是页面中会用到的数据
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), exception);

        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
            throw exception;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", exception);
        mv.setViewName("error/error");
        return mv;
    }

}


