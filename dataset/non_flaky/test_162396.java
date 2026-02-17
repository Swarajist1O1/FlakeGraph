class DummyClass_162396 {
    @Test
    public void testWaitingFails() {
        final DockerComposeContainer environment = new DockerComposeContainer(new File("src/test/resources/compose-test.yml"))
            .withExposedService("redis_1", REDIS_PORT, Wait.forHttp("/test").withStartupTimeout(Duration.ofSeconds(10)));
        VisibleAssertions.assertThrows("waiting on an invalid http path times out",
            RuntimeException.class,
            () -> environment.starting(Description.createTestDescription(Object.class, "name")));
    }

}