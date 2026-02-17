class DummyClass_135042 {
    @Test(expected = IOException.class)
    public void testIpV4AddressMultipleDots() throws IOException {
        NetworkUtils.parseIpv4Address("1..255.2");
    }

}