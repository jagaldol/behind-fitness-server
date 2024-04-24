#!/bin/bash

# logs 폴더 생성 확인
mkdir -p ~/logs/behind-fitness

# 기존에 실행 중인 Java 프로세스 종료
PID=$(ps -ef | grep '[j]ava -jar' | awk '{print $2}')
if [ ! -z "$PID" ]; then
  echo "기존 프로세스 종료: $PID"
  kill $PID
fi

date=`date +%y-%m-%dT%H-%M-%S`
filePath=~/logs/behind-fitness/springboot_nohup.$date.out

# 환경변수 세팅
set -a
source .env
set +a

# 애플리케이션 실행
nohup java -jar -Dspring.profiles.active=deploy build/libs/behind-fitness-1.0.jar >> "$filePath" 2>&1 &
echo "애플리케이션 실행 중..."
