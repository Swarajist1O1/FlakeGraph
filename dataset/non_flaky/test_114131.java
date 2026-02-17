class DummyClass_114131 {
    @Test
    public void localDateTimeAttributeConverterNotAcceptZonedTimeTest() {
        assertFails(() -> transformTo(converter, EnhancedAttributeValue.fromString("1988-05-21T00:12:00+01:00[Europe/Paris]")
                                                                       .toAttributeValue()));
    }

}