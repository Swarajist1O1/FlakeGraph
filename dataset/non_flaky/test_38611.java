class DummyClass_38611 {
    @Test(timeOut = 20000)
    public void testMsgKey() throws Exception {
        String argString = "%s -r 10 -u %s -m 500";
        String topic = testTopic + UUID.randomUUID().toString();
        String args = String.format(argString, topic, pulsar.getBrokerServiceUrl());
        Thread thread = new Thread(() -> {
            try {
                PerformanceProducer.main(args.split(" "));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Consumer<byte[]> consumer1 = pulsarClient.newConsumer().topic(topic).subscriptionName("sub-1")
                .subscriptionType(SubscriptionType.Key_Shared).subscribe();
        Consumer<byte[]> consumer2 = pulsarClient.newConsumer().topic(topic).subscriptionName("sub-1")
                .subscriptionType(SubscriptionType.Key_Shared).subscribe();

        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < 10; i++) {
            Message<byte[]> message = consumer1.receive(1, TimeUnit.SECONDS);
            if (message == null) {
                break;
            }
            count1++;
            consumer1.acknowledge(message);
        }
        for (int i = 0; i < 10; i++) {
            Message<byte[]> message = consumer2.receive(1, TimeUnit.SECONDS);
            if (message == null) {
                break;
            }
            count2++;
            consumer2.acknowledge(message);
        }
        //in key_share mode, only one consumer can get msg
        Assert.assertTrue(count1 == 0 || count2 == 0);

        consumer1.close();
        consumer2.close();
        thread.interrupt();
        while (thread.isAlive()) {
            Thread.sleep(1000);
        }

        //use msg key generator,so every consumer can get msg
        String newArgString = "%s -r 10 -u %s -m 500 -mk autoIncrement";
        String topic2 = testTopic + UUID.randomUUID().toString();
        String newArgs = String.format(newArgString, topic2, pulsar.getBrokerServiceUrl());
        Thread thread2 = new Thread(() -> {
            try {
                PerformanceProducer.main(newArgs.split(" "));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        Consumer newConsumer1 = pulsarClient.newConsumer().topic(topic2).subscriptionName("sub-2")
                .subscriptionType(SubscriptionType.Key_Shared).subscribe();
        Consumer newConsumer2 = pulsarClient.newConsumer().topic(topic2).subscriptionName("sub-2")
                .subscriptionType(SubscriptionType.Key_Shared).subscribe();
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < 10; i++) {
            Message<byte[]> message = newConsumer1.receive(1, TimeUnit.SECONDS);
            if (message == null) {
                break;
            }
            count1++;
            newConsumer1.acknowledge(message);
        }
        for (int i = 0; i < 10; i++) {
            Message<byte[]> message = newConsumer2.receive(1, TimeUnit.SECONDS);
            if (message == null) {
                break;
            }
            count2++;
            newConsumer2.acknowledge(message);
        }

        Assert.assertTrue(count1 > 0 && count2 > 0);
        thread2.interrupt();
        newConsumer1.close();
        newConsumer2.close();
    }

}