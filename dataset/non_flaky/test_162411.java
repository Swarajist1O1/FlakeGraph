class DummyClass_162411 {
    @Test
    public void testContainerInstanceProperties() {
        final ContainerState container = waitStrategy.getContainer();

        //check environment variable was set
        assertThat("Environment variable set correctly", Arrays.asList(Objects.requireNonNull(container.getContainerInfo()
            .getConfig().getEnv())), hasItem("bar=bar"));

        //check other container properties
        assertNotNull("Container id is not null", container.getContainerId());
        assertNotNull("Port mapped", container.getMappedPort(3000));
        assertThat("Exposed Ports", container.getExposedPorts(), hasItem(3000));

    }

}