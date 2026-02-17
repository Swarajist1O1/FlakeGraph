class DummyClass_114116 {
    @Test
    public void InstantAsStringAttributeConverterNotAcceptLocalDateTimeTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("1988-05-21T00:12:00.000000001")
                                                                       .toAttributeValue()));
    }

}