class DummyClass_177244 {
    @Test
    public void ipV6Only() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("bar.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("bar.com.", "1.1.1.1")),
                new DefaultDnsQuestion("bar.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("bar.com.", "::1"))
                                         .addRecord(ANSWER, newAddressRecord("bar.com.", "::1234:5678:90ab"))
                                         .addRecord(ANSWER, newAddressRecord("bar.com.",
                                                                             "2404:6800:4004:806::2013"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("bar.com")
                                                .port(8080)
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV6_ONLY)
                                                .build()) {

                assertThat(group.whenReady().get(10, TimeUnit.SECONDS)).containsExactly(
                        Endpoint.of("bar.com", 8080).withIpAddr("2404:6800:4004:806::2013"),
                        Endpoint.of("bar.com", 8080).withIpAddr("::1"),
                        Endpoint.of("bar.com", 8080).withIpAddr("::1234:5678:90ab"));
            }
        }
    }

}