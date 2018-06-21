# 动态修改日志级别
## pom文件中添加依赖
```java
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 修改application.yml文件
```yaml
management:
  security:
    enabled: false
```

## 查看系统的日志级别
访问http://localhost:8080/loggers,查看系统的日志级别
```json
{
    "levels": [
        "OFF",
        "ERROR",
        "WARN",
        "INFO",
        "DEBUG",
        "TRACE"
    ],
    "loggers": {
        "ROOT": {
            "configuredLevel": "INFO",
            "effectiveLevel": "INFO"
        },
        "com": {
            "configuredLevel": null,
            "effectiveLevel": "INFO"
        },
        "com.chenbk": {
            "configuredLevel": null,
            "effectiveLevel": "INFO"
        },
        "com.chenbk.boot": {
            "configuredLevel": "DEBUG",
            "effectiveLevel": "DEBUG"
        },
        "com.chenbk.boot.Application": {
            "configuredLevel": null,
            "effectiveLevel": "DEBUG"
        },
        "com.chenbk.boot.config": {
            "configuredLevel": null,
            "effectiveLevel": "DEBUG"
        },
        "com.chenbk.boot.config.LogAopProxy": {
            "configuredLevel": null,
            "effectiveLevel": "DEBUG"
        }  "effectiveLevel": "INFO"
    }
}
```
## 修改系统的日志级别
http://localhost:8080/loggers/com.chenbk.boot
```json
{
	"configuredLevel":"INFO"
}
```
修改成功后不会有数据返回