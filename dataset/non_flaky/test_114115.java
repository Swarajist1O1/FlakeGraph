class DummyClass_114115 {
    @Test
    public void InstantAsStringAttributeConverterExceedHigherBoundTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("+1000000001-01-01T00:00:00Z")
                                                                       .toAttributeValue()));
    }

}