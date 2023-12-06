FROM 315120000506.dkr.ecr.us-east-1.amazonaws.com/hotmart/alpine/openjdk/21/jre-minimal

ENV APP_NAME hotmart-voices

COPY ./build/libs/"${APP_NAME}"-0.0.1-SNAPSHOT.jar $SPRINGBOOT_JAR
COPY ./src/main/resources/application*.yml $ENV_HOME/
