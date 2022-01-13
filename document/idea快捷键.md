# 1.mac命令

  创建文件：touch xxx.txt

| 描述     | 命令          |
| -------- | ------------- |
| 创建文件 | touch xxx.txt |
|          |               |
|          |               |
|          |               |





# 2.Idea快捷键

| 描述                | Mac命令                   | windows命令     |
| :------------------ | ------------------------- | --------------- |
| 快速getter/setter   | control+回车              | alt+回车        |
| 注释//              | command+/                 | alt+/           |
| 注释/**/            | option+command+/          | alt+shift+/     |
| 导包/序列化         | option+回车               |                 |
| 代码补全            | option+command+v          |                 |
| Try catch代码块     | command+option+T          | ctrl+alt+l      |
| 删除行              | command+x                 | ctrl+x          |
| idea全屏幕          | control+command+f         |                 |
| 整行代码向上/下移动 | 整行代码向上/下移动       | ctrl+Shift+箭头 |
| 搜索/全局搜索       | command+f/command+shift+f |                 |
| 搜索方法名          | command+o                 | ctrl+n          |
| 方法中参数提醒      | command+p                 | ctrl+p          |
|                     |                           |                 |



# 3.Mac快捷键

| 描述           | 命令              |
| -------------- | ----------------- |
| 程序切换全屏幕 | command+control+f |
|                |                   |
|                |                   |



# 4.Mac文件地址

| 描述   | 地址               |
| ------ | ------------------ |
| 桌面   | /user/pine/Desktop |
| yisong | /user/pine/yisong  |
|        |                    |





```
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
