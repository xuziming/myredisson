# Spring Boot Starter

Integrates Redisson with Spring Boot library. Depends on [Spring Data Redis](https://github.com/redisson/redisson/tree/master/redisson-spring-data#spring-data-redis-integration) module.

Supports Spring Boot 1.3.x, 1.4.x, 1.5.x, 2.0.x, 2.1.x

<sub>Consider __[Redisson PRO](https://redisson.pro)__ version for advanced features and support by SLA.</sub>

## Usage

### 1. Add `redisson-spring-boot-starter` dependency into your project:

Maven

```xml
     <dependency>
         <groupId>org.redisson</groupId>
         <artifactId>redisson-spring-boot-starter</artifactId>
         <version>3.11.5</version>
     </dependency>
```

Gradle

```groovy
     compile 'org.redisson:redisson-spring-boot-starter:3.11.5'
```

### 2. Add settings into `application.settings` file

Common spring boot settings or Redisson settings could be used.

```properties
# common spring boot settings

spring.redis.database=
spring.redis.host=
spring.redis.port=
spring.redis.password=
spring.redis.ssl=
spring.redis.timeout=
spring.redis.cluster.nodes=
spring.redis.sentinel.master=
spring.redis.sentinel.nodes=

# Redisson settings

#path to redisson.yaml or redisson.json
spring.redis.redisson.config=classpath:redisson.yaml
```

### 3. Use Redisson through spring bean with `RedissonClient` interface or `RedisTemplate`/`ReactiveRedisTemplate` objects

