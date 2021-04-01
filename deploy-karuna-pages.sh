#!/bin/bash

cd /root/

docker-compose down

docker login -u mkalema -p e5f5eafa6f1bc37a3b0172f98339b09784bfda18 docker.pkg.github.com

docker pull docker.pkg.github.com/cutting-dsa/karuna-pages/karuna-pages

docker pull docker.pkg.github.com/cutting-dsa/karuna-pages/karuna-pages

docker-compose up --build  --remove-orphans -d