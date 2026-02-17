class DummyClass_77556 {
    @Test
    public void eachTestShouldUseANewPort() throws Throwable {
        final ResourceExtension resources = ResourceExtension.builder()
                .setTestContainerFactory(new GrizzlyTestContainerFactory())
                .build();
        Set<Integer> usedPorts = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            resources.before();
            final int port = resources.target("/").getUri().getPort();
            usedPorts.add(port);
        }
        assertThat(usedPorts).hasSizeGreaterThanOrEqualTo(2);
    }

}