class DummyClass_177254 {
    @ParameterizedTest
    public void partialIpV6Response(ResolvedAddressTypes resolvedAddressTypes) throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                // Respond AAAA record only.
                // Respond with NXDOMAIN for A.
                new DefaultDnsQuestion("partial.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("partial.com", "::1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("partial.com")
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(resolvedAddressTypes)
                                                .backoff(Backoff.fixed(500))
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("partial.com").withIpAddr("::1"));
            }
        }
    }

}