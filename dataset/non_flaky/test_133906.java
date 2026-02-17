class DummyClass_133906 {
    @Test
    public void testBadCascApply() {
        String config = payloadFromResource("/casc-bad.yml");
        RequestStatus success = api().apply(config);
        assertFalse(success.value());
    }

}