class DummyClass_38614 {
    @Test
    public void testDefaultIMessageFormatter() {
        IMessageFormatter msgFormatter = PerformanceProducer.getMessageFormatter("org.apache.pulsar.testclient.DefaultMessageFormatter");
        Assert.assertTrue(msgFormatter instanceof DefaultMessageFormatter);
    }

}