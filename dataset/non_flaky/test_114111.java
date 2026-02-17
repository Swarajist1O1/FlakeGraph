class DummyClass_114111 {
    @Test
    public void InstantAsStringAttributeConverterEpochPlusOneMilliTest() {
        verifyTransform(Instant.EPOCH.plusMillis(1), "1970-01-01T00:00:00.001Z");
    }

}