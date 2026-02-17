class DummyClass_162416 {
    @Test
    public void testLogConsumer() throws TimeoutException {
        WaitingConsumer logConsumer = new WaitingConsumer();
        DockerComposeContainer environment = new DockerComposeContainer(new File("src/test/resources/v2-compose-test.yml"))
            .withExposedService("redis_1", 6379)
            .withLogConsumer("redis_1", logConsumer);

        try {
            environment.starting(Description.EMPTY);
            logConsumer.waitUntil(frame -> frame.getType() == STDOUT && frame.getUtf8String().contains("Ready to accept connections"), 5, TimeUnit.SECONDS);
        } finally {
            environment.finished(Description.EMPTY);
        }
    }

}