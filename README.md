# 二手书交易平台
基于springboot，springcloud和vue的二手书交易平台

## oauth授权平台
使用springsecurity，oauth，redis，mybatis和mysql数据库搭建的授权平台  
主要分为三个功能:  
1. 其他服务提交的用户账号密码，进行认证，返回带有授权的jwt。
2. 其他服务来oauth提交jwt，进行jwt的验证，返回验证结果。
3. 注册用户。  

