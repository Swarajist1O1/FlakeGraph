class DummyClass_177236 {
    @Test
    public void testWithPrefixThatEndsWithDot() {
        final PropertiesEndpointGroup endpointGroup = PropertiesEndpointGroup.of(
                getClass().getClassLoader(), "server-list.properties", "serverA.hosts.");

        assertThat(endpointGroup.endpoints()).containsExactlyInAnyOrder(Endpoint.parse("127.0.0.1:8080"),
                                                                        Endpoint.parse("127.0.0.1:8081"),
                                                                        Endpoint.parse("127.0.0.1"));
    }

}