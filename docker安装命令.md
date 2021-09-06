# 1.Docker安装

## 1.1 mac Docker安装步骤

https://www.runoob.com/docker/macos-docker-install.html



## 1.2 Docker 镜像加速

https://www.runoob.com/docker/docker-mirror-acceleration.html



# 2.redis安装

> 参考文献：https://blog.csdn.net/qq_17623363/article/details/106418353

## 2.1 安装命令

#### 2.1.1 安装镜像

redis镜像库：https://hub.docker.com/_/redis?tab=tags

```java
docker pull redis:latest  // latest是版本号 例如：docker pull redis:5.0.13
```



#### 2.1.2 查看本地镜像

```java
docker images
```



## 2.2 准备redis的配置文件

#### 2.2.1 下载redis.conf

redis中文官方网站：http://www.redis.cn/download.html

#### 2.2.2 修改redis.conf

```html
bind 127.0.0.1 #注释掉这部分，使redis可以外部访问
daemonize no#用守护线程的方式启动
requirepass 你的密码#给redis设置密码
appendonly yes#redis持久化　　默认是no
tcp-keepalive 300 #防止出现远程主机强迫关闭了一个现有的连接的错误 默认是300
```



## 2.3 创建本地与docker映射的目录，即本地存放的位置

 创建数据持久化地址，例如：/Users/pine/yisong/data/redis/data

 Redis.conf地址：/Users/pine/yisong/data/redis



## 2.4 启动 docker redis

#### 2.4.1 启动命令

```shell
sudo docker run -p 6379:6379 --name redis -v /Users/pine/yisong/data/redis/data:/etc/redis/redis.conf  -v /Users/pine/yisong/data/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
```

-p 6379:6379:把容器内的6379端口映射到宿主机6379端口
-v /Users/pine/yisong/data/redis/data:/etc/redis/redis.conf：把宿主机配置好的redis.conf放到容器内的这个位置中
-v /Users/pine/yisong/data/redis/data:/data：把redis持久化的数据在宿主机内显示，做数据备份
redis-server /etc/redis/redis.conf：这个是关键配置，让redis不是无配置启动，而是按照这个redis.conf的配置启动
–appendonly yes：redis启动后数据持久化

#### 2.4.2 查看是否启动成功

查看是否成功启动：`sudo docker ps`

找到启动成功的redis的NAMES（其实就是启动命令中的 -name后面的redis）

#### 2.4.3 redis启动客户端

```shell
docker exec -it redis-test /bin/bash
redis-cli
```



# 3.rabbitmq

> 参考文献：https://segon.cn/install-rabbitmq-using-docker.html

## 3.1安装命令

#### 3.1.1 安装镜像

镜像地址：https://hub.docker.com/_/rabbitmq

```java
docker pull rabbitmq:3.8.5-management
```



## 3.2 创建本地与docker映射的目录，即本地存放的位置

 创建数据持久化地址，例如：/Users/pine/yisong/data/rabbitmq/data



## 3.3 启动 docker rabbitmq

#### 3.3.1 启动命令

```shell
docker run -d \
             --name rabbit \
             -p 5672:5672 \
             -p 15672:15672 \
             -v /Users/pine/yisong/data/rabbitmq/data:/var/lib/rabbitmq \
             --hostname my-rabbit \
             -e RABBITMQ_DEFAULT_USER=admin \
             -e RABBITMQ_DEFAULT_PASS=123456 \
             rabbitmq:3.9.5
```

`-d` docker 容器在后台运行
`--name` 定义一个容器的名字，如果没有指定，那么会自动生成一个随机数字符串当做UUID
`-p 5672:5672` 端口映射，`5672`应用访问端口
`-p 15672:15672` 端口映射，`15672`控制台Web端口号
`-v /data/docker/rabbitmq:/var/lib/rabbitmq`绑定一个数据卷，`/data/docker/rabbitmq`是刚才创建的本地数据卷
`--hostname my-rabbit` 主机名（RabbitMQ 的一个重要注意事项是它根据所谓的 `节点名称` 存储数据，默认为主机名）
`-e RABBITMQ_DEFAULT_USER=admin` 默认用户的用户名
`-e RABBITMQ_DEFAULT_PASS=123456` 默认用户的密码



#### 3.3.2 查看Web管理端

用浏览器打开管理端: [http://server-ip:15672](http://server-ip:15672/)

docker安装RabbitMQ后无法访问web界面问题：

我们将rabbitmq_management这个插件启动，然后就好了

```shell
docker exec -it rabbit rabbitmq-plugins enable rabbitmq_management
```

https://blog.csdn.net/fuqilee/article/details/106103680



Zys1994803

Harmay0825!
