class DummyClass_114120 {
    @Test
    public void InstantAsStringAttributeConverterNotAcceptLocalTimeTest() {
        assertFails(() -> transformTo(CONVERTER, EnhancedAttributeValue.fromString("00:12:00.000000001")
                                                                       .toAttributeValue()));
    }

}