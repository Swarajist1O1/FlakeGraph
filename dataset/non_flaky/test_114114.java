class DummyClass_114114 {
    @Test
    public void InstantAsStringAttributeConverterInvalidFormatTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("X")
                                                                       .toAttributeValue()));
    }

}