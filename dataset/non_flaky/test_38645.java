class DummyClass_38645 {
    @Test
    public void TestOpenAndReadSource() throws Exception {
        Map<String, Object> conf = Maps.newHashMap();
        StringSource stringSource = new StringSource();
        conf.put("name", "a1");
        conf.put("confFile", "./src/test/resources/flume/sink.conf");
        conf.put("noReloadConf", false);
        conf.put("zkConnString", "");
        conf.put("zkBasePath", "");
        Event event = EventBuilder.withBody("test event 1", Charsets.UTF_8);
        stringSource.open(conf, mockSourceContext);
        Thread.sleep(3 * 1000);
        sink.start();
        Transaction transaction = channel.getTransaction();

        transaction.begin();
        for (int i = 0; i < 10; i++) {
            channel.put(event);
        }
        transaction.commit();
        transaction.close();

        for (int i = 0; i < 5; i++) {
            Sink.Status status = sink.process();
            assertEquals(status, Sink.Status.READY);
        }

        assertEquals(sink.process(), Sink.Status.BACKOFF);
        stringSource.close();
    }

}