class DummyClass_77118 {
    @Test
    public void testValidIntegralValuesWithJavaSemantics() {
        schemaValidatorsConfig.setJavaSemantics(true);
        for (String validValue : validIntegralValues) {
            assertSame(JsonType.INTEGER,
                    getValueNodeType(DecimalNode.valueOf(new BigDecimal(validValue)), schemaValidatorsConfig),
                    validValue);
        }
    }

}