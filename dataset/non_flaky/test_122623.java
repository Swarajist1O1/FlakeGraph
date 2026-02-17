class DummyClass_122623 {
    @Test(expected = RuntimeException.class)
    public void throws_when_multiple_private_ipv4_addresses() {
        mock.addAddress("localhost", "38.3.4.2")
                .addAddress("localhost", "10.0.2.2")
                .addAddress("localhost", "10.0.2.3");
        mock.getIPv4Address("localhost");
    }

}