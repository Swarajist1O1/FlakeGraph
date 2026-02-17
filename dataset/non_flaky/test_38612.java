class DummyClass_38612 {
    @Test(timeOut = 20000)
    public void testCreatePartitions() throws Exception {
        String argString = "%s -r 10 -u %s -au %s -m 5 -np 10";
        String topic = testTopic + UUID.randomUUID().toString();
        String args = String.format(argString, topic, pulsar.getBrokerServiceUrl(), pulsar.getWebServiceAddress());
        Thread thread = new Thread(() -> {
            try {
                PerformanceProducer.main(args.split(" "));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        Assert.assertEquals(10, pulsar.getAdminClient().topics().getPartitionedTopicMetadata(topic).partitions);
    }

}