FROM golang:1.10.8-alpine

WORKDIR /go/src/consumer-go

COPY . .

RUN apk add librdkafka-dev pkgconf build-base git

RUN go get -v ./... \
 && go install -v ./...

CMD [ "consumer-go" ]
