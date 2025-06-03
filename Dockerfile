# Byggfas: Maven + JDK 23
FROM maven:3-eclipse-temurin-23 AS build
WORKDIR /opt/app
COPY . .
RUN mvn clean package -DskipTests

# Körfas: WildFly på Eclipse Temurin 23 med UBI 9
FROM eclipse-temurin:23-jre-ubi9-minimal

# WildFly & PostgreSQL-versioner
ARG WILDFLY_VERSION=35.0.1.Final
ARG WILDFLY_SHA1=35e61cfe2b14bab1f0644d4967090fe7de8590dd
ARG POSTGRESQL_DRIVER_VERSION=42.7.5
ARG POSTGRESQL_JDBC_DRIVER=postgresql-${POSTGRESQL_DRIVER_VERSION}.jar

ENV JBOSS_HOME=/opt/jboss/wildfly \
    LAUNCH_JBOSS_IN_BACKGROUND=true

# Skapa jboss-användare och struktur
RUN groupadd -r jboss -g 1000 && useradd -u 1000 -r -g jboss -m -d /opt/jboss -s /sbin/nologin -c "JBoss User" jboss

# Installera WildFly
RUN cd /opt/jboss && \
    curl -LO https://github.com/wildfly/wildfly/releases/download/${WILDFLY_VERSION}/wildfly-preview-${WILDFLY_VERSION}.tar.gz && \
    echo "${WILDFLY_SHA1}  wildfly-preview-${WILDFLY_VERSION}.tar.gz" | sha1sum -c && \
    tar xf wildfly-preview-${WILDFLY_VERSION}.tar.gz && \
    mv wildfly-preview-${WILDFLY_VERSION} ${JBOSS_HOME} && \
    rm wildfly-preview-${WILDFLY_VERSION}.tar.gz && \
    chown -R jboss:0 ${JBOSS_HOME} && chmod -R g+rw ${JBOSS_HOME}

# Ladda ner PostgreSQL JDBC-driver
RUN curl -L -o ${JBOSS_HOME}/standalone/deployments/postgresql.jar \
    https://jdbc.postgresql.org/download/${POSTGRESQL_JDBC_DRIVER} && \
    chmod 664 ${JBOSS_HOME}/standalone/deployments/postgresql.jar && \
    touch ${JBOSS_HOME}/standalone/deployments/postgresql.jar.dodeploy

# Kopiera CLI och entrypoint-skript
COPY --chown=jboss:0 docker/configure-datasource.cli /tmp/
COPY --chown=jboss:0 docker/entrypoint.sh /opt/jboss/
RUN chmod +x /opt/jboss/entrypoint.sh

# Kopiera WAR från build-steget
COPY --chown=jboss:0 --from=build /opt/app/target/*.war ${JBOSS_HOME}/standalone/deployments/

# Miljövariabler
ENV DB_HOST=postgres \
    DB_PORT=5432 \
    DB_NAME=example \
    DB_USER=postgres \
    DB_PASSWORD=postgres \
    DS_NAME=MovieDS \
    DS_JNDI=java:/jdbc/MovieDS

USER jboss

EXPOSE 8080

# Add health check
HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/health || exit 1

ENTRYPOINT ["/opt/jboss/entrypoint.sh"]
