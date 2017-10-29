# musical-giggle
Record the learning process

# lombok
https://projectlombok.org/  
1、@Data：Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode   
2、@Accessors：If chain is true, setters return this instead of void   
更多参考  
https://projectlombok.org/features/all  
https://projectlombok.org/api/

# Warning: Field injection is not recommended  
Spring Team recommends: "Always use constructor based dependency injection in your beans. Always use assertions for mandatory dependencies".

# spring-boot-starter-data-jpa  
spring.jpa.hibernate.ddl-auto：create、create-drop、update、validate、none  
    1）create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因
    2）create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除
    3）update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会  
    4）validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值  
    5）none：没有配置

@Id  
@GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
在javax.persistence.GenerationType中定义了以下几种可供选择的策略：  
    –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；  
    –AUTO： JPA自动选择合适的策略，是默认选项；  
    –SEQUENCE：通过序列产生主键，通过@SequenceGenerator注解指定序列名，MySql不支持这种方式  
    –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。  
@Entity和@Table(name = "t_user")，t_user是表名  
@Column(name = "字段名")

# log4j2  
log4j2 VS log4j、logback、sfl4j、JUL、JCL

# druid-spring-boot-starter

# spring-boot-starter-thymeleaf 

# spring-boot-starter-data-redis