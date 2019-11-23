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

**8.配置Spring Boot Actuator **     
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

**11eureka 集群**
    
     