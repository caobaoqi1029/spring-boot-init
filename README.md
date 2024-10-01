# SpringBoot 项目初始模板

基于 Java SpringBoot 的项目初始模板，整合了常用框架和主流业务的示例代码。

# 技术选型

| **类别**           | **项目**                     | **特性/描述**                             |
|------------------|----------------------------|---------------------------------------|
| 🌐 **主流框架 & 特性** | Spring Boot 2.7.x          | 快速构建应用程序，提供各种自动配置                     |
|                  | Spring MVC                 | 模型视图控制器架构，支持 RESTful 接口               |
|                  | MyBatis + MyBatis Plus     | 数据访问层，支持自动生成 SQL，开启分页                 |
|                  | Spring Boot 调试工具           | 提供调试支持，如 Spring Boot DevTools         |
|                  | Spring AOP                 | 切面编程，实现面向切面的业务逻辑                      |
|                  | Spring Scheduler           | 定时任务，定期执行特定任务                         |
|                  | Spring 事务注解                | 使用注解管理事务，保证事务的一致性                     |
| 🗄️ **数据存储**     | MySQL                      | 关系型数据库，用于存储业务数据                       |
|                  | Redis                      | 高性能缓存、分布式锁、会话管理等                      |
|                  | Elasticsearch              | 全文检索，支持复杂查询场景                         |
|                  | 腾讯云 COS                    | 存储文件及静态资源，如图片、文档等                     |
| 🛠️ **工具类**      | Easy Excel                 | Excel 文件的高效读写操作                       |
|                  | Hutool                     | Java 常用工具类库，提供丰富的工具方法                 |
|                  | Apache Commons Lang3       | 提供字符串、数组、日期等常用操作工具                    |
|                  | Lombok                     | 使用注解简化 Java 代码，减少样板代码                 |
| 🧑‍💻 **业务特性**   | 业务代码生成器                    | 自动生成 Service、Controller、数据模型代码，加速开发效率 |
|                  | Spring Session Redis 分布式登录 | 分布式环境下实现用户会话管理                        |
|                  | 全局请求响应拦截器                  | 统一记录请求和响应的日志                          |
|                  | 全局异常处理器                    | 统一处理异常，返回标准化的错误信息                     |
|                  | 自定义错误码                     | 定义和管理业务相关的错误码                         |
|                  | 封装通用响应类                    | 统一封装接口响应数据，提供标准化的返回结构                 |
|                  | Swagger + Knife4j 接口文档     | 自动生成 API 文档，便于测试和开发协作                 |
|                  | 自定义权限注解 + 全局校验             | 灵活配置权限注解，并支持全局验证                      |
|                  | 全局跨域处理                     | 解决跨域请求问题                              |
|                  | 长整数丢失精度解决                  | 处理长整数在前端展示时的精度丢失问题                    |
|                  | 多环境配置                      | 支持开发、测试、生产等不同环境的配置                    |
| 📋 **业务功能**      | 提供示例 SQL                   | 提供用户、帖子、帖子点赞、帖子收藏等表的示例 SQL            |
|                  | 用户管理                       | 用户登录、注册、注销、更新、检索、权限管理                 |
|                  | 帖子管理                       | 创建、删除、编辑、更新，数据库和 Elasticsearch 搜索     |
|                  | 帖子点赞/取消点赞                  | 支持用户对帖子进行点赞和取消点赞                      |
|                  | 帖子收藏/取消收藏/检索               | 支持用户收藏帖子、取消收藏以及检索已收藏的帖子               |
|                  | 帖子同步任务                     | 支持全量同步帖子到 Elasticsearch，增量同步通过定时任务进行  |
|                  | 微信登录                       | 支持通过微信开放平台进行第三方登录                     |
|                  | 微信公众号支持                    | 支持微信公众号的订阅、消息收发和自定义菜单配置               |
|                  | 文件上传                       | 支持按业务需求的文件上传                          |

# 快速上手

> [!TIP]
>
> 所有需要修改的地方都标记了 `todo`，便于大家找到修改的位置

## MySQL 数据库

1）修改 `application.yaml` 的数据库配置为你自己的：

```yml
spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/db
        username: root
        password: root
```

2）执行 `sql/create_table.sql` 中的数据库语句，自动创建库表

3）启动项目，访问 `http://localhost:8101/api/doc.html` 即可打开接口文档，不需要写前端就能在线调试接口了

![](docs/swagger.png)

## Redis 分布式登录

1）修改 `application.yml` 的 Redis 配置为你自己的：

```yml
spring:
    redis:
        host: localhost
        port: 6379
        database: 1
        timeout: 5000
```

2）修改 `application.yml` 中的 session 存储方式：

```yml
spring:
    session:
        store-type: redis
```

3）移除 `MainApplication` 类开头 `@SpringBootApplication` 注解内的 exclude 参数：

修改前：

```java
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
```

修改后：

```java
@SpringBootApplication
```

### Elasticsearch 搜索引擎

1）修改 `application.yml` 的 Elasticsearch 配置为你自己的：

```yml
spring:
    elasticsearch:
        uris: http://localhost:9200
        username: root
        password: root
```

2）复制 `sql/post_es_mapping.json` 文件中的内容，通过调用 Elasticsearch 的接口或者 Kibana Dev Tools 来创建索引（相当于数据库建表）

```
PUT post_v1
{
 参数见 sql/post_es_mapping.json 文件
}
```

3）开启同步任务，将数据库的帖子同步到 Elasticsearch

找到 job 目录下的 `FullSyncPostToEs` 和 `IncSyncPostToEs` 文件，取消掉 `@Component` 注解的注释，再次执行程序即可触发同步：

```java
// todo 取消注释开启任务
//@Component
```

## 业务代码生成器

支持自动生成 Service、Controller、数据模型代码，配合 MyBatisX 插件，可以快速开发增删改查等实用基础功能。

找到 `generate.CodeGenerator` 类，修改生成参数和生成路径，并且支持注释掉不需要的生成逻辑，然后运行即可。

```
// 指定生成参数
String packageName = "com.yupi.springbootinit";
String dataName = "用户评论";
String dataKey = "userComment";
String upperDataKey = "UserComment";
```

>[!TIP]
> 生成代码后，可以移动到实际项目中，并且按照 `// todo` 注释的提示来针对自己的业务需求进行修改。

