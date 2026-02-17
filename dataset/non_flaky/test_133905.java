class DummyClass_133905 {
    @Test
    public void testBadCascCheck() {
        String config = payloadFromResource("/casc-bad.yml");
        RequestStatus success = api().check(config);
        assertFalse(success.value());
    }

}