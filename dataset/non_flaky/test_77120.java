class DummyClass_77120 {
    @Test
    public void testWithLosslessNarrowing() {
        schemaValidatorsConfig.setLosslessNarrowing(true);
        for (String validValue : validIntegralValues) {
            assertSame(JsonType.INTEGER,
                    getValueNodeType(DecimalNode.valueOf(new BigDecimal("1.0")), schemaValidatorsConfig),
                    validValue);

            assertSame(JsonType.NUMBER,
                    getValueNodeType(DecimalNode.valueOf(new BigDecimal("1.5")), schemaValidatorsConfig),
                    validValue);
        }
    }

}