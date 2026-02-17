class DummyClass_122621 {
    @Test
    public void choose_ipv6_public_over_local() {
        mock.addAddress("localhost", "38.3.4.2")
                .addAddress("localhost", "10.0.2.2")
                .addAddress("localhost", "fe80::1")
                .addAddress("localhost", "2001::1");

        assertEquals(InetAddresses.forString("2001::1"), mock.getIPv6Address("localhost").get());
    }

}