# KNiotCloud-Backend

## SpringBootUsage

1.create database

mysql -uroot

create database db_example

2.create jar

mvn clean package

3.execute jar

cd target

java -jar spring-boot-api-0.1.0.jar

4.notices

//create tables in first time 

spring.jpa.hibernate.ddl-auto=none

//create tables in every time

spring.jpa.hibernate.ddl-auto=create 

5.api url
http://localhost:8080/swagger-ui.html