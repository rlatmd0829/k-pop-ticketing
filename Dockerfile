# 베이스 이미지로부터 시작
FROM openjdk:17-alpine3.14

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 애플리케이션 JAR 파일 복사
COPY build/libs/ticketing-0.0.1-SNAPSHOT.jar /app/ticketing-0.0.1-SNAPSHOT.jar

# 애플리케이션 실행 명령 설정
CMD ["java", "-jar", "ticketing-0.0.1-SNAPSHOT.jar"]
