**frontend: React**  
**npm start**  
**工程文件：npm run build**
***
**backend: springboot, mysql**  
**mvn spring-boot:run**  
**mvn clean install**  
**java -jar -Dserver.port=8083 target/demo-0.0.1-SNAPSHOT.jar**  
***
**nginx**  
**start: nginx**  
**stop: nginx -s stop**  
**有改动：nginx -s reload**  
**sudo nano /opt/homebrew/etc/nginx/nginx.conf**  

project description:  
frontend:  
1. Using Ant Design open source platform to create a list based table with select, sort and search by ID funcations  
2. Implemented download as excel files button, delete, and delete all buttons  
###
nginx:  
1. Resolve cors (cross-origin resource sharing) probelms using nginx  
2. Running spring boot application on multiple servers and using nginx to balance sending requests to these servers  
###
backend:  
1. Using MVC (Model View Controller) and three-tier architecture to structure the project  
2. Implemented RESTful API, added JWT authentication and spring security for user login  
3. Using Easy Excel from Alibaba for downloading database table as Excel files to local machines.  
4. Using Mybatis to communicate between mysql database, added transaction for data roll back   



bin/startup.sh -m standalone  
tail -f logs/start.out  
