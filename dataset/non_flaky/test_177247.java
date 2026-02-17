class DummyClass_177247 {
    @Test
    public void cname() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("a.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newBadAddressRecord("a.com.", true))
                                         .addRecord(ANSWER, newCnameRecord("a.com.", "b.com."))
                                         .addRecord(ANSWER, newAddressRecord("b.com.", "1.1.1.1")),
                new DefaultDnsQuestion("a.com.", AAAA),
                new DefaultDnsResponse(0).addRecord(ANSWER, newBadAddressRecord("a.com.", false))
                                         .addRecord(ANSWER, newCnameRecord("a.com.", "b.com."))
                                         .addRecord(ANSWER, newAddressRecord("b.com.", "::1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("a.com")
                                                .port(8080)
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_PREFERRED)
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("a.com", 8080).withIpAddr("1.1.1.1"),
                        Endpoint.of("a.com", 8080).withIpAddr("::1"));
            }
        }
    }

}