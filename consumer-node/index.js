const { load } = require('dotenv')

load()

const { KafkaClient, Consumer } = require('kafka-node')

const kafkaHost = process.env.KAFKA_HOST
const clientId = process.env.KAFKA_GROUP_ID
const topic = process.env.KAFKA_TOPIC

const client = new KafkaClient({ kafkaHost, clientId })
const consumer = new Consumer(
  client,
  [{ topic }]
)

console.log('Consumer started, press <CTRL> + C to exit')

consumer.on('message', ({ value }) => {
  console.log(value)
})
