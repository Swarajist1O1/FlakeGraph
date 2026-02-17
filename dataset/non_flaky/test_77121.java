class DummyClass_77121 {
    @Test
    public void testWithoutLosslessNarrowing() {
        schemaValidatorsConfig.setLosslessNarrowing(false);
        for (String validValue : validIntegralValues) {
            assertSame(JsonType.NUMBER,
                    getValueNodeType(DecimalNode.valueOf(new BigDecimal("1.0")), schemaValidatorsConfig),
                    validValue);

            assertSame(JsonType.NUMBER,
                    getValueNodeType(DecimalNode.valueOf(new BigDecimal("1.5")), schemaValidatorsConfig),
                    validValue);
        }

    }

}