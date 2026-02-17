class DummyClass_162437 {
    @Test(expected = NotFoundException.class)
    public void copyFromContainerShouldFailBecauseNoFileTest() throws NotFoundException, IOException, InterruptedException {

        try (final GenericContainer alpineCopyToContainer = new GenericContainer("alpine:3.2")
                        .withCommand("top")) {
            alpineCopyToContainer.start();
            alpineCopyToContainer.copyFileFromContainer("/home/test.txt", "src/test/resources/copy-from/test.txt");
        }
    }

}