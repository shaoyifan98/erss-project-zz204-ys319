FROM gradle:6.3.0-jdk14
RUN mkdir /deploy
WORKDIR /deploy/
ADD . /deploy/
EXPOSE 13579
EXPOSE 8080
ENTRYPOINT ["gradle", "bootRun"]
