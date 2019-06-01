require 'dotenv/load'
require 'kafka'

kafka_hosts = ENV['KAFKA_HOST']
group_id = ENV['KAFKA_GROUP_ID']
topic = ENV['KAFKA_TOPIC']

client = Kafka.new(kafka_hosts)
consumer = client.consumer(group_id: group_id)

trap('TERM') { consumer.stop }

consumer.subscribe(topic)

puts 'Consumer started, press <CTRL> + C to exit'

consumer.each_message do |message|
  puts message.value
end
