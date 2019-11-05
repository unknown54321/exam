
docker login 

docker build . --tag pgr301 --build-arg JAR_FILE=./target/dominiGeiger_exam-0.0.1-SNAPSHOT.jar

docker run <tag>/<repo>

Push to docker hub


docker tag <tag> <username>/<tag_remote>

docker push <username>/<tag_remote>
