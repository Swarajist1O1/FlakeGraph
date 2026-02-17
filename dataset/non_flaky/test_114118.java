class DummyClass_114118 {
    @Test
    public void InstantAsStringAttributeConverterNotAcceptZonedTimeTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("1988-05-21T00:12:00+01:00[Europe/Paris]")
                                                                       .toAttributeValue()));
    }

}