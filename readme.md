#DOCKER
In order to run cms application inside as a docker container it's necessary to do next steps related to docker 

## Pull mongo docker image
```
docker pull mongo
```
## Create docker network
```
docker network create cms-application
```
## Create mongo docker container
```
docker run -d --name mongodb --net cms-application -p 27017:27017 mongo
```
## Create docker image
The next command should be executed in the root folder of the project: 
```
mvn clean install docker:build
```
If a error appears here, first build the project from IDE or maven and then execute the command

## Create the container with app
 ```
docker run -d --name cms --link mongodb:mongodb --net cms-application -p 8080:8080 springfive/cms:latest
```
## Run the container
Running the container when it has been created 
```
docker start cms
```
