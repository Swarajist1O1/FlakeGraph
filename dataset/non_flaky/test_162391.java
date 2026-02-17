class DummyClass_162391 {
    @Test
    public void simpleRecursiveFileTest() throws TimeoutException {

        WaitingConsumer wait = new WaitingConsumer();

        final ToStringConsumer toString = new ToStringConsumer();

        GenericContainer container = new GenericContainer(
                new ImageFromDockerfile()
                        .withDockerfileFromBuilder(builder ->
                                builder.from("alpine:3.3")
                                        .copy("/tmp/foo", "/foo")
                                        .cmd("cat /foo/src/test/resources/test-recursive-file.txt")
                                        .build()
                        ).withFileFromFile("/tmp/foo", new File(".")))  // '.' is expected to be the project base directory, so all source code/resources should be copied in
                .withStartupCheckStrategy(new OneShotStartupCheckStrategy())
                .withLogConsumer(wait.andThen(toString));

        container.start();
        wait.waitUntilEnd(60, TimeUnit.SECONDS);

        final String results = toString.toUtf8String();

        assertTrue("The container has a file that was copied in via a recursive copy", results.contains("Used for DirectoryTarResourceTest"));
    }

}