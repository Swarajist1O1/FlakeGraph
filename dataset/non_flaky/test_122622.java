class DummyClass_122622 {
    @Test(expected = RuntimeException.class)
    public void throws_when_multiple_ipv6_addresses() {
        mock.addAddress("localhost", "2001::1")
                .addAddress("localhost", "2001::2");
        mock.getIPv6Address("localhost");
    }

}