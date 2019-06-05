const { load } = require('dotenv')

load()

const { NConsumer } = require('sinek')

const kafkaHost = process.env.KAFKA_HOST
const groupId = process.env.KAFKA_GROUP_ID
const topic = process.env.KAFKA_TOPIC

const consumer = new NConsumer(topic, {
  kafkaHost,
  groupId,
  noptions: {
    'enable.auto.commit': true
  }
})

console.log('Consumer started, press <CTRL> + C to exit')

consumer.on('error', (error) => console.error(error));
consumer.on('message', ({ value }) => {
  console.log(value)
})

consumer.connect()
  .then(() => consumer.consume())
  .catch((error) => console.error(error))
