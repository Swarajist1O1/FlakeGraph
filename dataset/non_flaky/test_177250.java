class DummyClass_177250 {
    @Test
    public void noPort() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("no-port.com.", A),
                new DefaultDnsResponse(0).addRecord(ANSWER, newAddressRecord("no-port.com", "1.1.1.1"))
        ))) {
            try (DnsAddressEndpointGroup group =
                         DnsAddressEndpointGroup.builder("no-port.com")
                                                .serverAddresses(server.addr())
                                                .resolvedAddressTypes(ResolvedAddressTypes.IPV4_ONLY)
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("no-port.com").withIpAddr("1.1.1.1"));
            }
        }
    }

}