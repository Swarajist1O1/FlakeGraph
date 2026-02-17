class DummyClass_162419 {
    @Test
    public void dockerfileBuilderWorks() {
        ImageFromDockerfile image = new ImageFromDockerfile()
                .withFileFromClasspath("test.txt", "mappable-resource/test-resource.txt")
                .withFileFromString("folder/someFile.txt", "hello")
                .withDockerfileFromBuilder(builder -> builder
                        .from("alpine:3.2")
                        .workDir("/app")
                        .add("test.txt", "test file.txt")
                        .run("ls", "-la", "/app/test file.txt")
                        .copy("folder/someFile.txt", "/someFile.txt")
                        .expose(80, 8080)
                        .cmd("while true; do cat /someFile.txt | nc -l -p 80; done")
                );

        verifyImage(image);
    }

}