FROM node:dubnium

WORKDIR /app

COPY . /app

RUN apt-get update \
 && apt-get install -y build-essential python librdkafka-dev libsasl2-dev libsasl2-modules openssl \
 && npm install

CMD [ "node", "index.js" ]
