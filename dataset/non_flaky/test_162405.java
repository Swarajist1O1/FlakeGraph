class DummyClass_162405 {
    @Test
    public void testFetchStdoutWithTimeout() throws TimeoutException {

        WaitingConsumer consumer = new WaitingConsumer();

        container.followOutput(consumer, STDOUT);

        assertThrows("a TimeoutException should be thrown", TimeoutException.class, () -> {
            consumer.waitUntil(frame -> frame.getType() == STDOUT && frame.getUtf8String().contains("seq=5"),
                    2, TimeUnit.SECONDS);
            return true;
        });
    }

}