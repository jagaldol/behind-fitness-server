# Fitness | Behind - server

![banner](./docs/banner.png)

당신만을 위한 비하인드 운동 관리 프로젝트 **Behind Fitness**의 백엔드 서버입니다.

## [Live Demo](https://behind-fitness.jagaldol.com/)

<p align="center">
    <img src="https://raw.githubusercontent.com/jagaldol/behind-fitness-client/main/docs/demo1.jpg" width="45%" />
    <img src="https://raw.githubusercontent.com/jagaldol/behind-fitness-client/main/docs/demo2.jpg" width="45%" />
</p>

> 자세한 내용은 [프론트엔드 레포지토리](https://github.com/jagaldol/behind-fitness-client)에서 확인하세요.

## Getting Started

### Set Environments Files

```shell
$ cp .env.example .env
$ cp .mysql.env.example .mysql.env
```

`.env` 파일들을 복사한 후 환경에 맞게 수정하시길 바랍니다.

### Run with Docker Compose

```shell
$ docker compose up -d
```

`mysql`, `redis` 와 함께 `spring boot` 서버가 **deploy** 환경으로 실행됩니다.

## Development

### Run Redis with Docker Compose

개발을 위해서는 `Redis`를 실행해야합니다.

```shell
$ docker compose -f docker-compose.redis.yml up -d
```

### Run Spring Boot Server

```shell
$ ./gradlew build
$ java -jar build/libs/app.jar
```

`Spring Boot` 서버가 로컬 환경에서 `8080`포트로 실행됩니다.

개발 환경(local)에서는 기본적으로 다음 계정이 설정되어있습니다:

- **test ID**: test@test.com
- **test password**: password

**❗주의: 로컬 서버의 DB는 Docker 컨테이너를 재실행할 때마다 초기화됩니다.**

### Run Development Spring Boot Server with Docker Compose

mysql이 없는 개발 서버를 `Docker Hub`에서 실행하려면, 아래 명령어를 사용하세요.

```shell
$ docker compose -f docker-compose.local.yml up -d
```

## ERD

[![운동 관리](https://github.com/jagaldol/behind-fitness-server/assets/84557643/f10f6d14-b12b-4c14-b09b-339ce776e1ca)](https://www.erdcloud.com/d/nzthWTtnCHPkjvShB)

> 더 많은 테이블은 `db/initdb.d/ddl.sql`을 확인하시기 바랍니다.

## APIs

| Method | url                               |
|--------|-----------------------------------|
| POST   | /login                            |
| POST   | /logout                           |
| POST   | /authentication                   |
| GET    | /users/mine                       |
| PUT    | /users/mine                       |
| POST   | /sports                           |
| GET    | /sports                           |
| PUT    | /sports/{sportId}                 |
| DELETE | /sports/{sportId}                 |
| POST   | /sessions                         |
| GET    | /sessions                         |
| GET    | /sessions/{sessionId}             |
| PUT    | /sessions/{sessionId}             |
| DELETE | /sessions/{sessionId}             |
| POST   | /sessions/{sessionId}/records     |
| PUT    | /sessions/records/{recordId}      |
| DELETE | /sessions/records/{recordId}      |
| POST   | /sessions/records/{recordId}/sets |
| PUT    | /sessions/records/sets/{setId}    |
| DELETE | /sessions/records/sets/{setId}    |

> 현재 구현된 API의 일부입니다.
