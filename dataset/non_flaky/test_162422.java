class DummyClass_162422 {
    @Test
    public void testIsRunning() {
        try (GenericContainer container = new GenericContainer().withCommand("top")) {
            assertFalse("Container is not started and not running", container.isRunning());
            container.start();
            assertTrue("Container is started and running", container.isRunning());
        }
    }

}