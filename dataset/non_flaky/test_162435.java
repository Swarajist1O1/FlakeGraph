class DummyClass_162435 {
    @Test
    public void createContainerCmdHookTest() {
        // Use random name to avoid the conflicts between the tests
        String randomName = Base58.randomString(5);
        try(
                GenericContainer container = new GenericContainer<>("redis:3.0.2")
                        .withCommand("redis-server", "--help")
                        .withCreateContainerCmdModifier(cmd -> cmd.withName("overrideMe"))
                        // Preserves the order
                        .withCreateContainerCmdModifier(cmd -> cmd.withName(randomName))
                        // Allows to override pre-configured values by GenericContainer
                        .withCreateContainerCmdModifier(cmd -> cmd.withCmd("redis-server", "--port", "6379"))
        ) {
            container.start();

            assertEquals("Name is configured", "/" + randomName, container.getContainerInfo().getName());
            assertEquals("Command is configured", "[redis-server, --port, 6379]", Arrays.toString(container.getContainerInfo().getConfig().getCmd()));
        }
    }

}