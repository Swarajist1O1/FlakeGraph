class DummyClass_162427 {
    @Test
    public void customLabelTest() {
        try (final GenericContainer alpineCustomLabel = new GenericContainer("alpine:3.2")
            .withLabel("our.custom", "label")
            .withCommand("top")) {

            alpineCustomLabel.start();

            Map<String, String> labels = alpineCustomLabel.getCurrentContainerInfo().getConfig().getLabels();
            assertTrue("org.testcontainers label is present", labels.containsKey("org.testcontainers"));
            assertTrue("our.custom label is present", labels.containsKey("our.custom"));
            assertEquals("our.custom label value is label", labels.get("our.custom"), "label");
        }
    }

}