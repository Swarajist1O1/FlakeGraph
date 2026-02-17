class DummyClass_114117 {
    @Test
    public void InstantAsStringAttributeConverterNotAcceptOffsetTimeTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("1988-05-21T00:12:00+01:00")
                                                                       .toAttributeValue()));
    }

}