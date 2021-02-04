# ssm-demo

Maven的多模块Spring+SpringMVC+MyBatis项目骨架

## 项目说明
整个项目通过maven来进行管理，根目录下面的pom.xml是父模块
- `ssm-web`负责SpringMVC处理前端请求
- `ssm-service`负责业务逻辑的编写
- `ssm-domain`负责管理相关实体类
- `ssm-dao`负责MyBatis进行数据访问
- `ssm-common`负责管理项目中所需用到的相关工具类

## 运行环境

- JDK1.8.0_271
- Tomcat 8.5.61
- MySQL 5.7.30
- spring.version 5.0.2.RELEASE

## 运行步骤

### 生成数据库

> 此项目默认是数据库为ssm

- 新建`ssm`数据库
- 在`ssm`数据库中运行项目中`ssm.sql`文件，生成相关数据库表

### 修改配置文件

要使项目正常运行，有三处配置文件需要修改
- `jdbc.properties`：更改为自己的数据库连接信息
- `important.properties`：目前该文件里面仅存放了文件的上传和下载功能对应于本机上的文件地址：根据需要进行修改即可
- `logback.xml`：根据自己需求修改logback日志文件输出地址，这里默认是存放在`D:\\Server\\logs\\ssm`目录下

### 部署Tomcat运行


## TODO
- 整合Redis
- 整合MQ
- ...