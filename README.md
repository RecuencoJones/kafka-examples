# Kafka examples

## Setup local kafka

We can create a basic kafka environment by using docker's `docker-compose` command.

```
docker-compose up -f ./kafka
```

For next points we will be using [Kafka Command-line Tools](https://www.cloudera.com/documentation/kafka/latest/topics/kafka_command_line.html)

## Create a topic

```
kafka-topics \
  --zookeeper localhost:2181 \
  --create \
  --topic t1 \
  --partitions 1 \
  --replication-factor 1
```

## Run a producer

```
kafka-console-producer --broker-list localhost:9092 --topic t1
```

## Configuration

The producer emits a message every 5 seconds.
The consumer simply reads the messages from the topic and outputs them to the console along with their offset.

| Variable | Description |
| --- | --- |
| `KAFKA_HOST` | Kafka broker host string, multiple comma separated hosts can be given  |
| `KAFKA_GROUP_ID` | Name of consumer group |
| `KAFKA_TOPIC` | Topic to read messages from |

### Example .env file

```
KAFKA_HOST=localhost:9092
KAFKA_GROUP_ID=test
KAFKA_TOPIC=t1
```

## Run a consumer

Check each directory for a guide with further information

- [.Net](./consumer-dotnet)
- [Go](./consumer-go)
- [Kotlin](./consumer-kotlin)
- [NodeJS](./consumer-node)
- [NodeJS (sinek)](./consumer-node-sinek)
- [Python](./consumer-python)
- [Ruby](./consumer-ruby)
