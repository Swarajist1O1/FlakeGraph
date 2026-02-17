class DummyClass_114126 {
    @Test
    public void localDateTimeAttributeConverterHigherBoundTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("9999999999-12-31T00:00:00")
                                                                       .toAttributeValue()));
    }

}