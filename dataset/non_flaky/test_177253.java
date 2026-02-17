class DummyClass_177253 {
    @ParameterizedTest
    public void partialIpV4Response(ResolvedAddressTypes resolvedAddressTypes) throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                // Respond A record only.
                // Respond with NXDOMAIN for AAAA.
                new DefaultDnsQuestion("partial.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("partial.com", "1.1.1.1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("partial.com")
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(resolvedAddressTypes)
                                                .backoff(Backoff.fixed(500))
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("partial.com").withIpAddr("1.1.1.1"));
            }
        }
    }

}