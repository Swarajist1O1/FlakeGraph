class DummyClass_122620 {
    @Test
    public void choose_sitelocal_ipv4_over_public() {
        mock.addAddress("localhost", "38.3.4.2")
                .addAddress("localhost", "10.0.2.2")
                .addAddress("localhost", "fe80::1")
                .addAddress("localhost", "2001::1");

        assertEquals(InetAddresses.forString("10.0.2.2"), mock.getIPv4Address("localhost").get());
    }

}