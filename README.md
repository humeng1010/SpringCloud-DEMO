# Spring Cloud DEMO

## 注册中心 DEMO

### Eureka DEMO

- `cloud-api-common` 通用软件包(entity)封装模块
- `cloud-provider-payment8001` 提供者,支付供应商模块
- `cloud-provider-payment8002` 提供者,支付供应商模块
- `cloud-consumer-order80` 消费者,订单消费模块(调用`cloud-provider-payment8001`中的创建支付和查询支付)
- `cloud-eureka-server7001` Eureka 服务注册与发现模块
- `cloud-eureka-server7002` Eureka 集群,和7001互相注册 