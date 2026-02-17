class DummyClass_114129 {
    @Test
    public void localDateTimeAttributeConverterNotAcceptInstantTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("1988-05-21T00:12:00.000000001Z")
                                                                       .toAttributeValue()));
    }

}