class DummyClass_162442 {
    @Test
    public void simpleTest() {

        DockerComposeContainer environment = new DockerComposeContainer(new File("src/test/resources/invalid-compose.yml"))
                    .withExposedService("something", 123);

        VisibleAssertions.assertThrows("starting with an invalid docker-compose file throws an exception",
                ContainerLaunchException.class,
                () -> {
                    environment.starting(Description.createTestDescription(Object.class, "name"));
                });
    }

}