class DummyClass_77201 {
    @Test
    public void doubleValueCoarsing() throws IOException {
        String schema = "{ \"$schema\":\"http://json-schema.org/draft-04/schema#\", \"type\": \"number\", \"maximum\": 1.7976931348623157e+308 }";
        String content = "1.7976931348623158e+308";

        JsonNode doc = mapper.readTree(content);
        JsonSchema v = factory.getSchema(mapper.readTree(schema));

        Set<ValidationMessage> messages = v.validate(doc);
        assertTrue(messages.isEmpty(), "Validation should succeed as by default double values are used by mapper");

        doc = bigDecimalMapper.readTree(content);
        messages = v.validate(doc);
        // "1.7976931348623158e+308" == "1.7976931348623157e+308" == Double.MAX_VALUE
        // new BigDecimal("1.7976931348623158e+308").compareTo(new BigDecimal("1.7976931348623157e+308")) > 0
        assertFalse(messages.isEmpty(), "Validation should not succeed because content is using bigDecimalMapper, and bigger than the maximum");

        /*
         * Note: technically this is where 1.7976931348623158e+308 rounding to 1.7976931348623157e+308 could be spotted,
         *       yet it requires a dedicated case of comparison BigDecimal to BigDecimal. Since values above
         *       1.7976931348623158e+308 are parsed as Infinity anyways (jackson uses double as primary type with later
         *       "upcasting" to BigDecimal, if property is set) adding a dedicated code block just for this one case
         *       seems infeasible.
         */
        v = factory.getSchema(bigDecimalMapper.readTree(schema));
        messages = v.validate(doc);
        assertFalse(messages.isEmpty(), "Validation should succeed as by default double values are used by mapper");
    }

}