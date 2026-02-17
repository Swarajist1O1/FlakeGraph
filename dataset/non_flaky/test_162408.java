class DummyClass_162408 {
    @Test
    public void testToStringConsumer() throws TimeoutException {

        WaitingConsumer waitingConsumer = new WaitingConsumer();
        ToStringConsumer toStringConsumer = new ToStringConsumer();

        Consumer<OutputFrame> composedConsumer = toStringConsumer.andThen(waitingConsumer);
        container.followOutput(composedConsumer);

        waitingConsumer.waitUntilEnd(30, TimeUnit.SECONDS);

        String utf8String = toStringConsumer.toUtf8String();
        assertTrue("the expected first value was found", utf8String.contains("seq=1"));
        assertTrue("the expected last value was found", utf8String.contains("seq=4"));
        assertFalse("a non-expected value was found", utf8String.contains("seq=42"));
    }

}