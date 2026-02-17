class DummyClass_114109 {
    @Test
    public void InstantAsStringAttributeConverterEpochMinusOneMilliTest() {
        verifyTransform(Instant.EPOCH.minusMillis(1), "1969-12-31T23:59:59.999Z");
    }

}