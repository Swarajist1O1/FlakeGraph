class DummyClass_162428 {
    @Test
    public void exceptionThrownWhenTryingToOverrideTestcontainersLabels() {
        assertThrows("When trying to overwrite an 'org.testcontainers' label, withLabel() throws an exception",
            IllegalArgumentException.class,
            () -> {
                new GenericContainer("alpine:3.2")
                    .withLabel("org.testcontainers.foo", "false");
            }
        );
    }

}