class DummyClass_38613 {
    @Test
    public void testNotExistIMessageFormatter() {
        IMessageFormatter msgFormatter = PerformanceProducer.getMessageFormatter("org.apache.pulsar.testclient.NonExistentFormatter");
        Assert.assertNull(msgFormatter);
    }

}