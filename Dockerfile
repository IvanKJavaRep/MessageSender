FROM openjdk:17-slim
LABEL MAINTAINER="crpt.ru"
ARG VERSION
ARG USERNAME=nonroot
ARG USER_GID=1000
ARG USER_UID=$USER_GID

RUN set -x \
    && addgroup --system --gid $USER_GID $USERNAME \
    && adduser --system  --ingroup $USERNAME --no-create-home --gecos "nonroot user" --uid $USER_UID $USERNAME



EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -jar /opt/smev3gate-adapter-app/smev3gate-adapter-app.jar

COPY build/libs/MessageSender-1.0-SNAPSHOT.jar /opt/smev3gate-adapter-app/smev3gate-adapter-app.jar

RUN  chown -R $USERNAME:$USERNAME /opt/smev3gate-adapter-app

USER $USERNAME
