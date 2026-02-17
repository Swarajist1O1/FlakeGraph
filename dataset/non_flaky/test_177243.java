class DummyClass_177243 {
    @Test
    public void ipV4Only() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("foo.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("foo.com.", "1.1.1.1"))
                                         .addRecord(ANSWER, newAddressRecord("unrelated.com", "1.2.3.4")),
                new DefaultDnsQuestion("foo.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("foo.com.", "::1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("foo.com")
                                                .port(8080)
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_ONLY)
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("foo.com", 8080).withIpAddr("1.1.1.1"));
            }
        }
    }

}