class DummyClass_114128 {
    @Test
    public void localDateTimeAttributeConverterInvalidNanoSecondsTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("0-01-01T00:00:00.9999999999")
                                                                       .toAttributeValue()));
    }

}