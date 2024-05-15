# Api Cubes4

## Group

- Quentin Alvernhe
- Arthur Frisch
- Aurélie Audemar
- Gauthier Antoine
- Théo Mafaro

## docker

docker run --name dbcube4 -p 8888:3306 -e MYSQL_ROOT_PASSWORD=admin -d mysql:latest
docker run --name phpmyadmin -d --link dbcube4:db -p 8181:80 phpmyadmin/phpmyadmin
