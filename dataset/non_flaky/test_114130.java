class DummyClass_114130 {
    @Test
    public void localDateTimeAttributeConverterNotAcceptOffsetTimeTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("1988-05-21T00:12:00+01:00")
                                                                       .toAttributeValue()));
    }

}