class DummyClass_114127 {
    @Test
    public void localDateTimeAttributeConverterExceedHigherBoundTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("9999999999-12-32T00:00:00")
                                                                       .toAttributeValue()));
    }

}