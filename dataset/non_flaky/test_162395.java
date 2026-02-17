class DummyClass_162395 {
    @Test
    public void testWaitOnMultipleStrategiesPassing() {
        final DockerComposeContainer environment = new DockerComposeContainer(new File("src/test/resources/compose-test.yml"))
            .withExposedService("redis_1", REDIS_PORT, Wait.forListeningPort())
            .withExposedService("db_1", 3306, Wait.forLogMessage(".*ready for connections.*\\s", 1))
            .withTailChildContainers(true);

        try {
            environment.starting(Description.createTestDescription(Object.class, "name"));
            VisibleAssertions.pass("Docker compose should start after waiting for listening port");
        } catch (RuntimeException e) {
            VisibleAssertions.fail("Docker compose should start after waiting for listening port with failed with: " + e);
        }
    }

}