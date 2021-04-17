FROM gradle:6.3.0-jdk14
RUN mkdir /deploy

WORKDIR /deploy/
ADD . /deploy/
RUN gradle clean build -x test

ADD . /deploy
EXPOSE 13579
ENTRYPOINT ["gradle", "bootRun"]
