package default

import io.github.cdimascio.dotenv.dotenv
import io.github.cdimascio.dotenv.Dotenv
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.time.Duration
import java.util.*

fun createConsumer(env: Dotenv): KafkaConsumer<String, String> {
  val props = Properties()

  props["bootstrap.servers"] = env["KAFKA_HOST"]
  props["group.id"] =  env["KAFKA_GROUP_ID"]
  props["key.deserializer"] = StringDeserializer::class.java
  props["value.deserializer"] = StringDeserializer::class.java

  return KafkaConsumer<String, String>(props)
}

fun getTopicConfig(env: Dotenv): String? {
  return env["KAFKA_TOPIC"]
}

fun subscribe(
    consumer: Consumer<String, String>,
    topics: List<String?>,
    callback: (ConsumerRecord<String, String>) -> Unit
) {
  consumer.subscribe(topics)

  while(true) {
    consumer.poll(Duration.ofSeconds(1))
      .iterator()
      .forEach {
        callback(it)
      }
  }
}

fun main(args: Array<String>) {
  val env = dotenv()
  val consumer = createConsumer(env)
  val topic = getTopicConfig(env)

  println("Consumer started, press <CTRL> + C to exit")

  subscribe(consumer, listOf(topic), { message ->
    println(message.value())
  })
}
