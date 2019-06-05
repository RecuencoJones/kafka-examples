using System;
using System.Threading;
using DotNetEnv;
using Confluent.Kafka;

class Program
{
    public static void Main(string[] args)
    {
        DotNetEnv.Env.Load();

        var conf = new ConsumerConfig
        {
            BootstrapServers = System.Environment.GetEnvironmentVariable("KAFKA_HOST"),
            GroupId = System.Environment.GetEnvironmentVariable("KAFKA_GROUP_ID"),
            AutoOffsetReset = AutoOffsetReset.Earliest
        };

        using (var consumer = new ConsumerBuilder<Ignore, string>(conf).Build())
        {
            consumer.Subscribe(System.Environment.GetEnvironmentVariable("KAFKA_TOPIC"));

            Console.WriteLine("Consumer started, press <CTRL> + C to exit");

            CancellationTokenSource cts = new CancellationTokenSource();
            Console.CancelKeyPress += (_, e) => {
                e.Cancel = true;

                cts.Cancel();
            };

            try
            {
                while (true)
                {
                    var message = consumer.Consume(cts.Token);
                    Console.WriteLine(message.Value);
                }
            }
            catch (OperationCanceledException)
            {
                consumer.Close();
            }
        }
    }
}
