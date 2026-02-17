class DummyClass_162407 {
    @Test
    public void testLogConsumer() throws TimeoutException {

        WaitingConsumer waitingConsumer = new WaitingConsumer();
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LOGGER);

        Consumer<OutputFrame> composedConsumer = logConsumer.andThen(waitingConsumer);
        container.followOutput(composedConsumer);

        waitingConsumer.waitUntil(frame -> frame.getType() == STDOUT && frame.getUtf8String().contains("seq=2"));
    }

}