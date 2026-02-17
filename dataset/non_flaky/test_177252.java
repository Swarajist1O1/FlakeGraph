class DummyClass_177252 {
    @Test
    public void backoffOnEmptyResponse() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                // Respond with empty records.
                new DefaultDnsQuestion("empty.com.", A), new DefaultDnsResponse(0),
                new DefaultDnsQuestion("empty.com.", AAAA), new DefaultDnsResponse(0)
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("empty.com")
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_PREFERRED)
                                                .backoff(Backoff.fixed(500))
                                                .build()) {

                await().untilAsserted(() -> assertThat(group.attemptsSoFar).isGreaterThan(2));
                assertThat(group.endpoints()).isEmpty();

                // Start to respond correctly.
                server.setResponses(ImmutableMap.of(
                        new DefaultDnsQuestion("empty.com.", A),
                        new DefaultDnsResponse(0)
                                .addRecord(ANSWER, newAddressRecord("empty.com", "1.1.1.1", 1)),
                        new DefaultDnsQuestion("empty.com.", AAAA),
                        new DefaultDnsResponse(0)
                                .addRecord(ANSWER, newAddressRecord("empty.com", "::1", 1))));

                await().untilAsserted(() -> assertThat(group.endpoints()).containsExactly(
                        Endpoint.of("empty.com").withIpAddr("1.1.1.1"),
                        Endpoint.of("empty.com").withIpAddr("::1")));
            }
        }
    }

}