class DummyClass_177248 {
    @Test
    public void mixedLoopbackAddresses() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("foo.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("foo.com.", "127.0.0.1")),
                new DefaultDnsQuestion("foo.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("foo.com.", "::1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("foo.com")
                                                .port(8080)
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_PREFERRED)
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("foo.com", 8080).withIpAddr("127.0.0.1"));
            }
        }
    }

}