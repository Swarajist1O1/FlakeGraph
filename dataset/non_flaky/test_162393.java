class DummyClass_162393 {
    @Test
    public void simpleRecursiveClasspathResourceTest() throws TimeoutException {
        // This test combines the copying of classpath resources from JAR files with the recursive TAR approach, to allow JARed classpath resources to be copied in to an image

        WaitingConsumer wait = new WaitingConsumer();

        final ToStringConsumer toString = new ToStringConsumer();

        GenericContainer container = new GenericContainer(
                new ImageFromDockerfile()
                        .withDockerfileFromBuilder(builder ->
                                builder.from("alpine:3.3")
                                        .copy("/tmp/foo", "/foo")
                                        .cmd("ls -lRt /foo")
                                        .build()
                        ).withFileFromClasspath("/tmp/foo", "/recursive/dir"))          // here we use /org/junit as a directory that really should exist on the classpath
                .withStartupCheckStrategy(new OneShotStartupCheckStrategy())
                .withLogConsumer(wait.andThen(toString));

        container.start();
        wait.waitUntilEnd(60, TimeUnit.SECONDS);

        final String results = toString.toUtf8String();

        // ExternalResource.class is known to exist in a subdirectory of /org/junit so should be successfully copied in
        assertTrue("The container has a file that was copied in via a recursive copy from a JAR resource", results.contains("content.txt"));
    }

}