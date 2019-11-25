# spring_cloud_parent
这是一个开始学习spring cloud的练习项目，从零开始，在该项目上会继承各种框架。此文件记录学习过程。
go...
https://docs.spring.io/spring/docs/current/javadoc-api/
引入cloud新技术组件
1. pom引入技术坐标
2.@Enablexxxxx
3.业务代码

版本问题 后面针对maven 项目名改变
https://github.com/spring-projects/spring-cloud/wiki/Spring-Cloud-Edgware-Release-Notes


----------------------
**1.从开始建项目开始学习**
    
    1.1创建普通的maven项目 也就pom父工程
    1.1.1删除src目录 修改pom文件，详见partent pom文件
        因为我们要学习springclould 所以引入了springclould(spring-cloud-dependencies)
**2.公共子模块**
    
    2.1 测试使用下lombok 代码中可以省略无参有参构造 get set 方法 tostring。（试试玩的，生产上还是不要使用）
    2.2 mvn clean  mvn install 后给其他模块引用，达到通用目的
    eg:        <dependency><!--引入自己api通用包，可以使用api的实体-->
                   <artifactId>spring_cloud_parent</artifactId>
                   <groupId>com.nojava</groupId>
                   <version>${project.version}</version>
               </dependency>
**3.创建一个服务提供者**
    
    约定》配置》编码    
    spring_cloud_provider_dept_8001
    
**4.创建一个消费端**
    
    spring_cloud_consumer_dept


微服务化的核心就是将一站式应用，根据业务拆分成一个个的服务，彻底的去耦合，每一个微服务提供单个业务功能
，一个服务做一件事，从技术角度看是一个小而独立的处理过程，能够自行单独启动或销毁，拥有自己独立的数据库。

**5.Eureka 服务注册于发现(对比dubbo+zookeeper) https://github.com/Netflix/eureka**    

    两大组件 Eureka Server和Eureka Client
    Eureka Server 提供服务注册  各个服务启动后会在eureka server中注册，eurekaservice就会存放服务节点信息
    Eureka Client 是java客户端，用于简化与Eureka Service交互，客户端内置了一个使用轮询负载算法均衡器。在应
    用启动后，会向eureka service 发送心跳。如果eureka service在多个心跳内没有接收到某个节点的心跳默认30s，
    eureka service 会从服务注册表中把这个节点移除 默认90s
    
    Spring Cloud Eureka 常用配置及说明
    https://www.iteye.com/blog/glf2002lucky-2428874
    
**6.创建服务注册中心**

    spring_cloud_eureka_7001
    添加pom依赖
    添加application.yml
    启动访问http://127.0.0.1:7001/ 注册中心地址 如果出现spring Eureka 表示启动成功
    
**7.服务注册eureka**
    
    将服务入住到eureka 
    修改pom文件 注意版本（请看最上方版本地址）
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
    @EnableEurekaClient
    
    eureka.client.serviceUrl.defaultZone={eureka地址}
    
    先启动eureka 服务端 在启动客户端
     在客户端中spring.pplication.name 可以在eureka 页面上找到该实例名  页面上大写

**8.配置Spring Boot Actuator**   

    参考：https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-endpoints
    
    优化项目
        监控和管理Spring Boot应用，比如健康检查、审计、统计和HTTP追踪等。
    使用：
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>
        </dependencies>
     
     配置注册微服务info信息
        修改主机名名称：
                 每个微服务地址都是ip：port  修改服务名称地址 可以起标识作用，类似修改为域名
                 eureka:
                   instance:
                     instance-id: spring_cloud_dept8001 #修改服务名称
         访问地址ip信息提示：    
          eureka:
            instance:        
             prefer-ip-address: true      #访问路径显示ip地址 
         配置info信息：
            1.配置actuator pom
            2.yum 中想获取mavenpom项目信息 可以通过在父pom文件中配置
                  <build>
                    <finalName>spring_cloud_parent</finalName>
                    <resources>
                      <resource>
                        //表示这个目录下可以进行被maven操作
                        <directory>src/main/resources</directory>
                        <filtering>true</filtering>
                      </resource>
                    </resources>
                
                    <plugins>
                      <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-resources-plugin</artifactId>
                        <configuration>
                          <delimiters>
                            <!--//表示取resources中以$*$的信息-->
                            <delimiter>$</delimiter>
                          </delimiters>
                        </configuration>
                      </plugin>
                
                      <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                      </plugin>
                    </plugins>
                  </build>
             3.yum 配置信息
                注意如果获取mavne pom 信息注意 2配置中有$   所以.yml 取值配置为$xxx$
                            
**9.eureka 自我保护机制**
    
       微服务不与eureka 断开连接  可以能为网络原因， eureka 则会继续保留该微服务信息，而不是删除该信息。

**10.服务发现**       

        @Autowired
        private DeptService deptService;
        
        @EnableDiscoveryClient

**11.eureka 集群**
    

**12.eureka 与zookeeper 区别**

    eureka AP
    zookeeper CP
    
    理解ACID  CAP （三选二）
    
    P （分区容错性）固定  因为我们集群在不同环境 所以必须
    C 强一致性
    A 可用性

**13.spring cloud Ribbon** 

    客户端负载均衡   
    引入相关pom依赖。 rabbit eureka (客户端   因为使用rabbit 要结合eureka) config  (同eureka)
    ribbon 软负载均衡客户端组件，

    核心组件IRULE 负载均衡算法
        
        com.myrule.MySelfRule
        
     Ribbon+RestTemplate 完成负载均衡   
**14.Feign 负载均衡**        
    
    声明示web服务客户端
    接口+注解
    Feign 与Ribbon 区别
    
    使用过程：

**15.Hystrix 断路器（保险丝） **
    
    分布式系统面临的问题：服务雪崩
    
    Hystrix 用于分布式系统的延迟和容错的开源库。
    
    服务熔断：
        应对雪崩效应的一种微服务链路保护机制。
        
        当扇出链路的某个服务器不可用，或者响应时间太长，会进行服务的降级， 进而熔断该节点的微服务的调用，快速返回错误响应信息
        当检测到该微服务节点调用响应正常后恢复调用链路， 在springcloud 框架中熔断机制通过hystrix实现，hystrix会监控微服务间
        调用的状况，当失败的调用到一定阈值，默认5s20次调用失败就会启动熔断机制，熔断机制的注解是@HystrixCommand。
        
       *针对的是服务端
        
        
    服务降级：
        降级处理是在客户端完成的，与服务端没有关系。    
        
        *针对的是客户端
    Feign Hystrix支持
            
    hystrixdashboard
        准实时的调用监控
        对监控内容转换成可视化界面
    
        参考：https://www.cnblogs.com/hejianjun/p/8670693.html
-----
**最后遇到的坑**
    
    1.1 idea启动不起来，我就下了最新版的idea
       遇到问题： 首先要给项目配置jdk
       项目本来是jdk1.8  新版的为11 我想着没事 用jdk11 启动项目报错。
       java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present
       百度原因：
        因为JAXB-API是java ee的一部分，在jdk9中没有在默认的类路径中；
        java ee api在jdk中还是存在的，默认没有加载而已，jdk9中引入了模块的概念，可以使用
        模块命令--add-modules java.xml.bind引入jaxb-api;
        解决办法
        https://blog.csdn.net/Alger_magic/article/details/83041811
        
        在你的POM 引入如下依赖。
        <dependency>
                    <groupId>javax.xml.bind</groupId>
                    <artifactId>jaxb-api</artifactId>
                    <version>2.3.0</version>
                </dependency>
                <dependency>
                    <groupId>com.sun.xml.bind</groupId>
                    <artifactId>jaxb-impl</artifactId>
                    <version>2.3.0</version>
                </dependency>
                <dependency>
                    <groupId>org.glassfish.jaxb</groupId>
                    <artifactId>jaxb-runtime</artifactId>
                    <version>2.3.0</version>
                </dependency>
                <dependency>
                    <groupId>javax.activation</groupId>
                    <artifactId>activation</artifactId>
                    <version>1.1.1</version>
                </dependency>
                
         但是我想着还是配置了1.8的jdk 项目启动没问题。       
    1.2 feign  
        nested exception is feign.FeignException: status 404 reading DeptClientServi
        接口服务地址配错了 导致404了  地址从消费地址copy过来的  -_-||
        
        百度了一下：
            如果出现这样错误，还有可能是参数问题， 因为@PathVariable 这种注解可能导致参数不同，所以有这种注解的也需要
            加上，      
            
    1.3 http://hystrix-app:port/hystrix.stream 不起作用
          spring boot2.0之后不提供hystrix.stream节点处理方法      
          单体应用的监控 应该为 http://hystrix-app:port/actuator/hystrix.stream    Actuator 2.x 以后 endpoints 全部在/actuator下
          
          为服务实例添加 endpoint
          在需要监控的微服务上加该配置信息
          management:
            endpoints:
              web:
                exposure:
                  include: hystrix.stream
    1.4   http://localhost:9001/actuator/hystrix.stream 一直在ping 没有显示。
        导致 http://localhost:9002/hystrix/monitor 一直loding
        
        原因： 改监控只针对进行熔断的方法起作用 ，其他方法没有作用。 我还将@HystrixCommand 这个注释掉了 想测测服务降级。。搞了两个小时。          