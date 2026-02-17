class DummyClass_177230 {
    @Test
    public void propertiesWithoutDefaultPort() {
        final PropertiesEndpointGroup endpointGroup = PropertiesEndpointGroup.of(PROPS, "serverA.hosts");

        assertThat(endpointGroup.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8080"),
                                                                        Endpoint.parse("127.0.0.1:8081"),
                                                                        Endpoint.parse("127.0.0.1"));
    }

}