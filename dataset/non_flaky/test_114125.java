class DummyClass_114125 {
    @Test
    public void localDateTimeAttributeConverterLowerBoundTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("-9999999999-01-01T00:00")
                                                                       .toAttributeValue()));
    }

}