class DummyClass_114132 {
    @Test
    public void localDateTimeAttributeConverterNotAcceptLocalTimeTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("00:12:00.000000001")
                                                                       .toAttributeValue()));
    }

}