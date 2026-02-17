class DummyClass_162431 {
    @Test
    public void exceptionThrownWhenMappedPortNotFound() throws IOException {
        assertThrows("When the requested port is not mapped, getMappedPort() throws an exception",
                IllegalArgumentException.class,
                () -> {
                    return redis.getMappedPort(666);
                });
    }

}