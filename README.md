# J2EE_Blog

主要是个肯定会遇到的Bug

如果报错类似[THYMELEAF][http-nio-8080-exec-1] Exception processing templat...
就去相应的前端页面ctrl + f fragments 把这个单词前面的下划线去掉就能跑了（注释里的也要删）

目前数据库里有一条信息 由于用md5加密了 所以密码是秘文 要想自己插入用户数据的话需要去util包下的MD5Utils的psvm里自己对密码进行一个加密 然后放到数据库里

如果懒得这么做就直接：
用户名 jk
密码 111111

咱们在写代码过程中踩的雷和学到的新知识都可以往这个README里放 

这里先放我目前学到的新知识：
@RequestMapping
RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。

//使数据库自动生成相关实体
@Entity(name = "t_blog")

//id标示主键
//GeneratedValue 自动生成
@Id
@GeneratedValue

//1的一端是被维护的
@OneToMany(mappedBy = "blog")

@ManyToMany(cascade = {CascadeType.PERSIST})


//@Temporal(TemporalType.TIMESTAMP) 会得到形如'HH:MM:SS' 格式的日期
@Temporal(TemporalType.TIMESTAMP)
private Date createTime;


//不加注解无法使用service
@Service
public class UserServiceImpl implements UserService{
    //注入UserRepository
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}


//User 和 User的主键类型
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}


第二种写法可以通过@RequestParam(required = false)设置为非必传。因为required值默认是true，所以默认必传
第二种写法可以通过@RequestParam("userId")或者@RequestParam(value = "userId")指定参数名
第二种写法可以通过@RequestParam(defaultValue = "0")指定参数默认值


