class DummyClass_38644 {
    @Test
    public void TestOpenAndWriteSink() throws Exception {
        Map<String, Object> conf = Maps.newHashMap();
        StringSink stringSink = new StringSink();
        conf.put("name", "a1");
        conf.put("confFile", "./src/test/resources/flume/source.conf");
        conf.put("noReloadConf", false);
        conf.put("zkConnString", "");
        conf.put("zkBasePath", "");
        stringSink.open(conf, mockSinkContext);
        send(stringSink, 100);

        Thread.sleep(3 * 1000);
        Transaction transaction = channel.getTransaction();
        transaction.begin();
        Event event = channel.take();

        Assert.assertNotNull(event);
        Assert.assertNotNull(mockRecord);

        verify(mockRecord, times(100)).ack();
        transaction.commit();
        transaction.close();
    }

}