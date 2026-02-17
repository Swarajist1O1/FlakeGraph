class DummyClass_133903 {
    @Test
    public void testCascCheck() {
        String config = payloadFromResource("/casc.yml");
        RequestStatus success = api().check(config);
        assertTrue(success.value());
    }

}