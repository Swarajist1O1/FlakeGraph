class DummyClass_162399 {
    @Test(timeout = 60_000L)
    public void pullingNonExistentImageFailsGracefully() {

        assertThrows("Pulling a nonexistent container will cause an exception to be thrown",
                ContainerFetchException.class, () -> {
                    return new GenericContainer("richnorth/nonexistent:latest");
                });
    }

}