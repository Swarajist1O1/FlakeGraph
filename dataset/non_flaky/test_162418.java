class DummyClass_162418 {
    @Test
    public void customizableImage() {
        ImageFromDockerfile image = new ImageFromDockerfile() {
            @Override
            protected void configure(BuildImageCmd buildImageCmd) {
                super.configure(buildImageCmd);

                List<String> dockerfile = Arrays.asList(
                        "FROM alpine:3.2",
                        "RUN echo 'hello from Docker build process'",
                        "CMD yes"
                );
                withFileFromString("Dockerfile", String.join("\n", dockerfile));

                buildImageCmd.withNoCache(true);
            }
        };

        verifyImage(image);
    }

}