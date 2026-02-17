class DummyClass_177255 {
    @Test
    public void srv() throws Exception {
        try (TestDnsServer server = new TestDnsServer(ImmutableMap.of(
                new DefaultDnsQuestion("foo.com.", SRV),
                new DefaultDnsResponse(0).addRecord(ANSWER, newSrvRecord("foo.com.", 1, 2, "a.foo.com."))
                                         .addRecord(ANSWER, newSrvRecord("foo.com.", 3, 4, "b.foo.com."))
                                         .addRecord(ANSWER, newSrvRecord("unrelated.com.", 0, 0, "asdf.com."))
                                         .addRecord(ANSWER, newTooShortSrvRecord("foo.com."))
                                         .addRecord(ANSWER, newBadNameSrvRecord("foo.com."))
        ))) {
            try (DnsServiceEndpointGroup group =
                         DnsServiceEndpointGroup.builder("foo.com")
                                                .serverAddresses(server.addr())
                                                .build()) {

                assertThat(group.whenReady().get()).containsExactly(
                        Endpoint.of("a.foo.com", 2).withWeight(1),
                        Endpoint.of("b.foo.com", 4).withWeight(3));
            }
        }
    }

}