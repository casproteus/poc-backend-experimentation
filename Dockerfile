FROM docker-platform-dev.artifactory.tsp.cld.touchtunes.com/build/ubuntu-22.04:0.2.0-jdk17 as build

WORKDIR /root/m4/poc-backend-experimentation
COPY . ./

RUN ln -s /usr/bin/python3 /usr/bin/python && \
  mvn package -B \
      -Dcheckstyle.skip \
      -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn \
      -DtestSkipIntegration=true

FROM docker-apps.artifactory.tsp.cld.touchtunes.com/base/app:0.9.0

# define default credentials file name
ENV APP_CONFIG=m4-edge-api.env

# Install JDK17
RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-17-jdk && \
    ln -s /usr/lib/jvm/java-17-openjdk-amd64 /usr/lib/jvm/default-java


WORKDIR /opt/app
COPY --from=build /root/m4/poc-backend-experimentation/target/ABTestingPOC-0.0.1-SNAPSHOT.jar ./

RUN useradd appuser

CMD ["gosu","appuser","java", "-Duser.timezone=UTC", "-Dfile.encoding=UTF-8", "-jar", "ABTestingPOC-0.0.1-SNAPSHOT.jar"]
