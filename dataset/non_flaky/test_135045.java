class DummyClass_135045 {
    @Test(expected = IOException.class)
    public void testIpV4Hostname2() throws IOException {
        NetworkUtils.parseIpv4Address("ff");
    }

}