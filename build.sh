#!/usr/bin/env bash

docker login

docker build . --tag geigerteller --build-arg JAR_FILE=./target/dominiGeiger_exam-0.0.1-SNAPSHOT.jar

docker tag geigerteller unknown54321/exam

docker push unknown54321/exam
