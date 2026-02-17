class DummyClass_77119 {
    @Test
    public void testValidIntegralValuesWithoutJavaSemantics() {
        schemaValidatorsConfig.setJavaSemantics(false);
        for (String validValue : validIntegralValues) {
            assertSame(JsonType.NUMBER,
                    getValueNodeType(DecimalNode.valueOf(new BigDecimal(validValue)), schemaValidatorsConfig),
                    validValue);
        }
    }

}