# spring_cloud_parent
这是一个开始学习spring cloud的练习项目，从零开始，在该项目上会继承各种框架。此文件记录学习过程。
go...
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
    
