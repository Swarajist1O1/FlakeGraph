class DummyClass_114121 {
    @Test
    public void InstantAsStringAttributeConverterNotAcceptMonthDayTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("05-21")
                                                                       .toAttributeValue()));
    }

}