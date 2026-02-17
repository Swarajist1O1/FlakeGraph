class DummyClass_162394 {
    @Test
    public void testWaitOnListeningPort() {
        final DockerComposeContainer environment = new DockerComposeContainer(new File("src/test/resources/compose-test.yml"))
            .withExposedService("redis_1", REDIS_PORT, Wait.forListeningPort());

        try {
            environment.starting(Description.createTestDescription(Object.class, "name"));
            VisibleAssertions.pass("Docker compose should start after waiting for listening port");
        } catch (RuntimeException e) {
            VisibleAssertions.fail("Docker compose should start after waiting for listening port with failed with: " + e);
        }
    }

}