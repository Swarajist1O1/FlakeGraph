class DummyClass_114104 {
    @Test
    public void defaultConvertersThrowExceptions() {
        assertThat(DefaultVisitor.INSTANCE.convert(EnhancedAttributeValue.nullValue())).isEqualTo(null);

        assertDefaultConversionFails(EnhancedAttributeValue.fromString("foo"));
        assertDefaultConversionFails(EnhancedAttributeValue.fromNumber("1"));
        assertDefaultConversionFails(EnhancedAttributeValue.fromBoolean(true));
        assertDefaultConversionFails(EnhancedAttributeValue.fromBytes(SdkBytes.fromUtf8String("")));
        assertDefaultConversionFails(EnhancedAttributeValue.fromSetOfStrings(Collections.emptyList()));
        assertDefaultConversionFails(EnhancedAttributeValue.fromSetOfNumbers(Collections.emptyList()));
        assertDefaultConversionFails(EnhancedAttributeValue.fromSetOfBytes(Collections.emptyList()));
        assertDefaultConversionFails(EnhancedAttributeValue.fromListOfAttributeValues(Collections.emptyList()));
        assertDefaultConversionFails(EnhancedAttributeValue.fromMap(Collections.emptyMap()));
    }

}