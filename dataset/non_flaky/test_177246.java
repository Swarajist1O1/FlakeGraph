class DummyClass_177246 {
    @Test
    public void platformDefault() throws Exception {
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
                                                .build()) {

                assertThat(group.whenReady().get()).contains(
                        Endpoint.of("baz.com", 8080).withIpAddr("1.1.1.1"));
            }
        }
    }

}