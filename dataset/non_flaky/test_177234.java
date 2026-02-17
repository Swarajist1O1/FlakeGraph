class DummyClass_177234 {
    @Test
    public void pathWithDefaultPort() throws Exception {
        final URL resourceUrl = getClass().getClassLoader().getResource("server-list.properties");
        assert resourceUrl != null;
        final Path resourcePath = new File(resourceUrl.getFile()).toPath();
        final PropertiesEndpointGroup endpointGroupA = PropertiesEndpointGroup.builder(
                resourcePath, "serverA.hosts").defaultPort(80).build();
        assertThat(endpointGroupA.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8080"),
                                                                         Endpoint.parse("127.0.0.1:8081"),
                                                                         Endpoint.parse("127.0.0.1:80"));
        endpointGroupA.close();
    }

}