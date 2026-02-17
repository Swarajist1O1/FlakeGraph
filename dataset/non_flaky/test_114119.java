class DummyClass_114119 {
    @Test
    public void InstantAsStringAttributeConverterNotAcceptLocalDateTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("1988-05-21")
                                                                       .toAttributeValue()));
    }

}