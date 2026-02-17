class DummyClass_114113 {
    @Test
    public void InstantAsStringAttributeConverterExceedLowerBoundTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("-1000000001-12-31T23:59:59.999999999Z")
                                                                       .toAttributeValue()));
    }

}