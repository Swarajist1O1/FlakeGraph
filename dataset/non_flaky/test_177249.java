class DummyClass_177249 {
    @Test
    public void ipV4MappedOrCompatibleAddresses() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("bar.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newCompatibleAddressRecord("bar.com.", "1.1.1.1"))
                                         .addRecord(ANSWER, newCompatibleAddressRecord("bar.com.", "1.1.1.2"))
                                         .addRecord(ANSWER, newMappedAddressRecord("bar.com.", "1.1.1.1"))
                                         .addRecord(ANSWER, newMappedAddressRecord("bar.com.", "1.1.1.3"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("bar.com")
                                                .port(8080)
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV6_ONLY)
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("bar.com", 8080).withIpAddr("1.1.1.1"),
                        Endpoint.of("bar.com", 8080).withIpAddr("1.1.1.2"),
                        Endpoint.of("bar.com", 8080).withIpAddr("1.1.1.3"));
            }
        }
    }

}