# Fitness | Behind - server

Behind 프로젝트의 첫번째 운동 관리 프로그램의 백엔드 서버입니다.

## ERD

[![pt 관리](https://github.com/jagaldol/behind-fitness-server/assets/84557643/f10f6d14-b12b-4c14-b09b-339ce776e1ca)](https://www.erdcloud.com/d/nzthWTtnCHPkjvShB)

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
