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
docker exec -it redis /bin/bash
redis-cli
```



# 3.rabbitmq

> 参考文献：https://segon.cn/install-rabbitmq-using-docker.html

## 3.1安装命令

#### 3.1.1 安装镜像

镜像地址：https://hub.docker.com/_/rabbitmq?tab=tags

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



# 4.rocketmq安装

> 参考文献：https://blog.csdn.net/ming19951224/article/details/109063041



## 4.1 namesrv服务

#### 4.1.1 安装镜像

```shell
docker pull rocketmqinc/rocketmq
```

#### 4.1.2 创建namesrv数据存储路径

```shell
mkdir -p /Users/pine/yisong/data/rocketmq/data/namesrv/logs  /Users/pine/yisong/data/rocketmq/data/namesrv/store
```

#### 4.1.3 构造namesrv容器

```shell
docker run -d \
--restart=always \
--name rmqnamesrv \
-p 9876:9876 \
-v /Users/pine/yisong/data/rocketmq/data/namesrv/logs:/root/logs \
-v /Users/pine/yisong/data/rocketmq/data/namesrv/store:/root/store \
-e "MAX_POSSIBLE_HEAP=100000000" \
rocketmqinc/rocketmq \
sh mqnamesrv 
```



## 4.2 创建broker节点

##### 4.2.1 创建broker数据存储路径

```shell
mkdir -p /Users/pine/yisong/data/rocketmq/conf
cd /Users/pine/yisong/data/rocketmq/conf
touch broker.conf
```



#### 4.2.2 创建配置文件

```
vi /docker/rocketmq/conf/broker.conf
# 所属集群名称，如果节点较多可以配置多个
brokerClusterName = DefaultCluster
#broker名称，master和slave使用相同的名称，表明他们的主从关系
brokerName = broker-a
#0表示Master，大于0表示不同的slave
brokerId = 0
#表示几点做消息删除动作，默认是凌晨4点
deleteWhen = 04
#在磁盘上保留消息的时长，单位是小时
fileReservedTime = 48
#有三个值：SYNC_MASTER，ASYNC_MASTER，SLAVE；同步和异步表示Master和Slave之间同步数据的机制；
brokerRole = ASYNC_MASTER
#刷盘策略，取值为：ASYNC_FLUSH，SYNC_FLUSH表示同步刷盘和异步刷盘；SYNC_FLUSH消息写入磁盘后才返回成功状态，ASYNC_FLUSH不需要；
flushDiskType = ASYNC_FLUSH
# 设置broker节点所在服务器的ip地址
brokerIP1 = 192.168.13.6
```



#### 4.2.3 构造broker容器

```shell
docker run -d  \
--restart=always \
--name rmqbroker \
--link rmqnamesrv:namesrv \
-p 10911:10911 \
-p 10909:10909 \
-v  /Users/pine/yisong/data/rocketmq/data/broker/logs:/root/logs \
-v  /Users/pine/yisong/data/rocketmq/data/broker/store:/root/store \
-v /Users/pine/yisong/data/rocketmq/conf/broker.conf:/opt/rocketmq-4.4.0/conf/broker.conf \
-e "NAMESRV_ADDR=namesrv:9876" \
-e "MAX_POSSIBLE_HEAP=200000000" \
rocketmqinc/rocketmq \
sh mqbroker -c /opt/rocketmq-4.4.0/conf/broker.conf 
```



## 4.3 创建rockermq-console

#### 4.3.1 拉取镜象

```shell
docker pull pangliang/rocketmq-console-ng
```



#### 4.3.2 构造rockermq-console容器

```shell
docker run -d \
--restart=always \
--name rmqadmin \
-e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.0.134:9876 \
-Dcom.rocketmq.sendMessageWithVIPChannel=false" \
-p 9999:8080 \
pangliang/rocketmq-console-ng
```



#### 4.3.3 访问rockermq-console

 192.168.13.6:9999



# 5.Zookeeper安装

## 5.1 下载Zookeeper镜像(默认最新)

docker pull zookeeper



## 5.2 启动容器并添加映射

docker run --privileged=true -d --name zookeeper --publish 2181:2181 -d zookeeper:latest



## 5.3 查看容器是否启动

docker ps



## 5.4 mac 普通安装

#### 启动zookeeper服务端

1）第一种方式 ：默认是到bin目录下

输入命令 ./zkServer.sh start启动命令

​       ./zkServer.sh status查看状态

​       ./zkServer.sh stop 停止命令



#### 启动 dubbo-admin

下载地址：https://github.com/apache/dubbo-admin/tree/develop

下载下来之后进入dubob-admin,执行打包

```java
mvn clean package -Dmaven.test.skip=true
```

Dubbo-admin启动命令：

打完包之后进入dubob-admin，执行如下命令

```shell
mvn --projects dubbo-admin-server spring-boot:run
```





# 6 Elasticsearch

## 6.1 mac普通安装

### 6.1.1 Elasticsearch

下载地址：https://www.elastic.co/cn/downloads/elasticsearch#ga-release

#### 下载完成之后解压，在config文件中找到elasticsearch.yml,添加以下参数:

node.name: node-1  
cluster.initial_master_nodes: ["node-1"]  
xpack.ml.enabled: false 
http.cors.enabled: true
http.cors.allow-origin: /.*/

#### 启动命令：

​	sh ./bin/elasticsearch

#### 访问地址：

 http://localhost:9200/



### 6.1.2 kibana安装

下载地址：https://www.elastic.co/cn/downloads/kibana

下载完成之后解压，在config文件中找到kibana.yml,添加以下参数（支持中文）:

i18n.locale: "zh-CN"



#### 启动命令：

​	sh ./bin/kibana

#### 访问地址：

http://localhost:5601/



### 6.1.3 Elasticsearch-head

git clone git://github.com/mobz/elasticsearch-head.git

cd elasticsearch-head

npm install

npm run start

http://localhost:9100/



### 6.1.3 ik分词器

官网：https://github.com/medcl/elasticsearch-analysis-ik

下载地址：https://github.com/medcl/elasticsearch-analysis-ik/releases

根据es版本下载相应版本包。

解压到 es/plugins/ik中。

重启es
