class DummyClass_162404 {
    @Test
    public void testFetchStdout() throws TimeoutException {

        WaitingConsumer consumer = new WaitingConsumer();

        container.followOutput(consumer, STDOUT);

        consumer.waitUntil(frame -> frame.getType() == STDOUT && frame.getUtf8String().contains("seq=2"),
                30, TimeUnit.SECONDS);
    }

}