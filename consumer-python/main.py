from kafka import KafkaConsumer
from dotenv import load_dotenv, find_dotenv
from os import getenv

load_dotenv(find_dotenv())

kafka_hosts = getenv('KAFKA_HOST')
topic = getenv('KAFKA_TOPIC')
group_id = getenv('KAFKA_GROUP_ID')

consumer = KafkaConsumer(
  topic,
  bootstrap_servers=[kafka_hosts],
  group_id=group_id
)

print('Consumer started, press <CTRL> + C to exit')

for message in consumer:
  data = message.value.decode('utf-8')

  print("%s" % (data))
