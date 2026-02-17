class DummyClass_162397 {
    @Test
    public void testWaitOnOneOfMultipleStrategiesFailing() {
        final DockerComposeContainer environment = new DockerComposeContainer(new File("src/test/resources/compose-test.yml"))
            .withExposedService("redis_1", REDIS_PORT, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(10)))
            .waitingFor("db_1", Wait.forLogMessage(".*test test test.*\\s", 1).withStartupTimeout(Duration.ofSeconds(10)))
            .withTailChildContainers(true);

        VisibleAssertions.assertThrows("waiting on one failing strategy to time out",
            RuntimeException.class,
            () -> environment.starting(Description.createTestDescription(Object.class, "name")));
    }

}