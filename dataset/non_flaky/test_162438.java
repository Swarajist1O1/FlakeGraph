class DummyClass_162438 {
    @Test
    public void shouldCopyFileFromContainerTest() throws IOException, InterruptedException {
        final File tempResultFolder = Files.createTempDir();

        try (final GenericContainer alpineCopyToContainer = new GenericContainer("alpine:3.2")
                .withCommand("top")) {

            alpineCopyToContainer.start();
            final MountableFile mountableFile = MountableFile.forClasspathResource("test_copy_to_container.txt");
            alpineCopyToContainer.copyFileToContainer(mountableFile, "/home/");
            alpineCopyToContainer.copyFileFromContainer("/home/test_copy_to_container.txt",
                    tempResultFolder.getAbsolutePath() + "/test_copy_from_container.txt");

            File expectedFile = new File(mountableFile.getResolvedPath());
            File actualFile = new File(tempResultFolder.getAbsolutePath() + "/test_copy_from_container.txt");
            assertTrue("Files aren't same ", FileUtils.contentEquals(expectedFile,actualFile));
        }
    }

}