class DummyClass_177245 {
    @Test
    public void ipV4AndIpV6() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("baz.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("baz.com.", "1.1.1.1")),
                new DefaultDnsQuestion("baz.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("baz.com.", "::1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("baz.com")
                                                .port(8080)
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_PREFERRED)
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("baz.com", 8080).withIpAddr("1.1.1.1"),
                        Endpoint.of("baz.com", 8080).withIpAddr("::1"));
            }
        }
    }

}