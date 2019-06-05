package main

import (
	"fmt"
	"os"

	"github.com/joho/godotenv"
	"gopkg.in/confluentinc/confluent-kafka-go.v1/kafka"
)

func main() {
	godotenv.Load()

	kafkaHosts := os.Getenv("KAFKA_HOST")
	groupID := os.Getenv("KAFKA_GROUP_ID")
	topic := os.Getenv("KAFKA_TOPIC")

	consumer, err := kafka.NewConsumer(&kafka.ConfigMap{
		"bootstrap.servers": kafkaHosts,
		"group.id":          groupID,
	})

	defer consumer.Close()

	if err != nil {
		panic(err)
	}

	consumer.Subscribe(topic, nil)

	fmt.Printf("Consumer started, press <CTRL> + C to exit\n")

	for {
		msg, _ := consumer.ReadMessage(-1)

		if msg != nil {
			fmt.Printf("%v\n", string(msg.Value))
		}
	}
}
