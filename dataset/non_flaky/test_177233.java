class DummyClass_177233 {
    @Test
    public void resourceWithDefaultPort() {
        final PropertiesEndpointGroup endpointGroupA =
                PropertiesEndpointGroup.builder(getClass().getClassLoader(),
                                                "server-list.properties",
                                                "serverA.hosts")
                                       .defaultPort(80)
                                       .build();

        final PropertiesEndpointGroup endpointGroupB =
                PropertiesEndpointGroup.builder(getClass().getClassLoader(),
                                                "server-list.properties",
                                                "serverB.hosts")
                                       .defaultPort(8080)
                                       .build();

        assertThat(endpointGroupA.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8080"),
                                                                         Endpoint.parse("127.0.0.1:8081"),
                                                                         Endpoint.parse("127.0.0.1:80"));
        assertThat(endpointGroupB.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8082"),
                                                                         Endpoint.parse("127.0.0.1:8083"));
    }

}