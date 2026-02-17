class DummyClass_177251 {
    @Test
    public void backoff() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of())) { // Respond nothing.
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("backoff.com")
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_PREFERRED)
                                                .backoff(Backoff.fixed(500))
                                                .build()) {

                await().untilAsserted(() -> assertThat(group.attemptsSoFar).isGreaterThan(2));
                assertThat(group.endpoints()).isEmpty();

                // Start to respond correctly.
                server.setResponses(ImmutableMap.of(
                        new DefaultDnsQuestion("backoff.com.", A),
                        new DefaultDnsResponse(0)
                                .addRecord(ANSWER, newAddressRecord("backoff.com", "1.1.1.1", 1)),
                        new DefaultDnsQuestion("backoff.com.", AAAA),
                        new DefaultDnsResponse(0)
                                .addRecord(ANSWER, newAddressRecord("backoff.com", "::1", 1))));

                await().untilAsserted(() -> assertThat(group.endpoints()).containsExactly(
                        Endpoint.of("backoff.com").withIpAddr("1.1.1.1"),
                        Endpoint.of("backoff.com").withIpAddr("::1")));
            }
        }
    }

}