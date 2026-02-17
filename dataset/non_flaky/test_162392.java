class DummyClass_162392 {
    @Test
    public void simpleRecursiveFileWithPermissionTest() throws TimeoutException {

        WaitingConsumer wait = new WaitingConsumer();

        final ToStringConsumer toString = new ToStringConsumer();

        GenericContainer container = new GenericContainer(
                new ImageFromDockerfile()
                        .withDockerfileFromBuilder(builder ->
                                builder.from("alpine:3.3")
                                        .copy("/tmp/foo", "/foo")
                                        .cmd("ls", "-al", "/")
                                        .build()
                        ).withFileFromFile("/tmp/foo", new File("/mappable-resource/test-resource.txt"),
                        0754))
                .withStartupCheckStrategy(new OneShotStartupCheckStrategy())
                .withLogConsumer(wait.andThen(toString));

        container.start();
        wait.waitUntilEnd(60, TimeUnit.SECONDS);

        String listing = toString.toUtf8String();

        assertThat("Listing shows that file is copied with mode requested.",
                Arrays.asList(listing.split("\\n")),
                exactlyNItems(1, allOf(containsString("-rwxr-xr--"), containsString("foo"))));
    }

}