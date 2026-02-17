class DummyClass_162406 {
    @Test
    public void testFetchStdoutWithNoLimit() throws TimeoutException {

        WaitingConsumer consumer = new WaitingConsumer();

        container.followOutput(consumer, STDOUT);

        consumer.waitUntil(frame -> frame.getType() == STDOUT && frame.getUtf8String().contains("seq=2"));
    }

}