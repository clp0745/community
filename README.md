## clp0745
学习使用

## 资料
[Spring 文档](https://spring.io/guides/)<br/>
[Spring Web](https://spring.io/guides/gs/serving-web-content/)<br>
[Bootstrap](https://v3.bootcss.com/getting-started/)

## 工具
Git

[ApiPost](https://www.apipost.cn/download.html)

## 脚本
```sql
CREATE TABLE USER 
(
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN CHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
);
```