class DummyClass_162417 {
    @Test
    public void simpleDockerfileWorks() {
        ImageFromDockerfile image = new ImageFromDockerfile()
                .withFileFromString("folder/someFile.txt", "hello")
                .withFileFromClasspath("test.txt", "mappable-resource/test-resource.txt")
                .withFileFromClasspath("Dockerfile", "mappable-dockerfile/Dockerfile");

        verifyImage(image);
    }

}