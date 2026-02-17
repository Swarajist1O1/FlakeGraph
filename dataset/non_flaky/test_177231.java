class DummyClass_177231 {
    @Test
    public void propertiesWithDefaultPort() {
        final PropertiesEndpointGroup endpointGroupA = PropertiesEndpointGroup.builder(PROPS, "serverA.hosts")
                                                                              .defaultPort(80)
                                                                              .build();
        final PropertiesEndpointGroup endpointGroupB = PropertiesEndpointGroup.builder(PROPS, "serverB.hosts")
                                                                              .defaultPort(8080)
                                                                              .build();

        assertThat(endpointGroupA.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8080"),
                                                                         Endpoint.parse("127.0.0.1:8081"),
                                                                         Endpoint.parse("127.0.0.1:80"));
        assertThat(endpointGroupB.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8082"),
                                                                         Endpoint.parse("127.0.0.1:8083"));
    }

}