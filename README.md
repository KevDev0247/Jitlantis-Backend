# Jitus-Backend

## Jitus Team Roles and Contributions
Roles:
- Feng Yuan: Cofounder, Product Manager
- Yonggang Su: Cofounder, System Architect, Frontend Developer, Mobile Developer
- Kevin Zhijun Wang: Cofounder, Web Full stack developer, Mobile Developer
- Ying Quan: UI & UX designer

Contributions:
- Product Management: Feng Yuan
- Marketing: Feng Yuan
- Requirement Analysis: Feng Yuan, Yongggang Su, Kevin Zhijun Wang
- System Architecture design: Yonggang Su
- Web Frontend Development: Yonggang Su, Kevin Zhijun Wang
- Web Backend Development: Kevin Zhijun Wang
- UI & UX design: Ying Quan


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