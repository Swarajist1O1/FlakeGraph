class DummyClass_133904 {
    @Test
    public void testCascApply() {
        String config = payloadFromResource("/casc.yml");
        RequestStatus success = api().apply(config);
        assertTrue(success.value());
    }

}