class DummyClass_177235 {
    @Test
    public void pathWithoutDefaultPort() {
        final URL resourceUrl = getClass().getClassLoader().getResource("server-list.properties");
        assert resourceUrl != null;
        final Path resourcePath = new File(resourceUrl.getFile()).toPath();
        final PropertiesEndpointGroup endpointGroup = PropertiesEndpointGroup.of(
                resourcePath, "serverA.hosts");
        assertThat(endpointGroup.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8080"),
                                                                        Endpoint.parse("127.0.0.1:8081"),
                                                                        Endpoint.parse("127.0.0.1"));
        endpointGroup.close();
    }

}