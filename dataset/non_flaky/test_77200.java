class DummyClass_77200 {
    @Test
    public void negativeDoubleOverflowTest() throws IOException {
        String[][] values = new String[][]{
//            maximum,                           value
//                both of these get parsed into double (with a precision loss) as  1.7976931348623157E+308
                {"1.79769313486231571E+308", "1.79769313486231572e+308"},
//                while underflow in not captures in previous case (unquoted number is parsed as double)
//                it is captured if value is passed as string, which is correctly parsed by BidDecimal
//                thus effective comparison is between
//                maximum 1.7976931348623157E+308  and
//                value   1.79769313486231572e+308
//                {"1.79769313486231571E+308",        "\"1.79769313486231572e+308\""},
                {"1.7976931348623157E+309", "1.7976931348623157e+309"},
                {"1.7976931348623157E+309", "\"1.7976931348623157e+309\""},
                {"1.000000000000000000000001E+400", "1.000000000000000000000001E+401"},
                {"1.000000000000000000000001E+400", "\"1.000000000000000000000001E+401\""},
                {"1.000000000000000000000001E+400", "1.000000000000000000000002E+400"},
                {"1.000000000000000000000001E+400", "\"1.000000000000000000000002E+400\""},
                {"1.000000000000000000000001E+400", "1.0000000000000000000000011E+400"},
                {"1.000000000000000000000001E+400", "\"1.0000000000000000000000011E+400\""},
        };

        for (String[] aTestCycle : values) {
            String maximum = aTestCycle[0];
            String value = aTestCycle[1];
            String schema = format(NUMBER, maximum);
            SchemaValidatorsConfig config = new SchemaValidatorsConfig();
            config.setTypeLoose(true);
            // Schema and document parsed with just double
            JsonSchema v = factory.getSchema(mapper.readTree(schema), config);
            JsonNode doc = mapper.readTree(value);
            Set<ValidationMessage> messages = v.validate(doc);
            assertTrue(messages.isEmpty(), format("Maximum %s and value %s are interpreted as Infinity, thus no schema violation should be reported", maximum, value));

            // document parsed with BigDecimal

            doc = bigDecimalMapper.readTree(value);
            Set<ValidationMessage> messages2 = v.validate(doc);
            if (Double.valueOf(maximum).equals(Double.POSITIVE_INFINITY)) {
                assertTrue(messages2.isEmpty(), format("Maximum %s and value %s are equal, thus no schema violation should be reported", maximum, value));
            } else {
                assertFalse(messages2.isEmpty(), format("Maximum %s is smaller than value %s ,  should be validation error reported", maximum, value));
            }


            // schema and document parsed with BigDecimal
            v = factory.getSchema(bigDecimalMapper.readTree(schema), config);
            Set<ValidationMessage> messages3 = v.validate(doc);
            //when the schema and value are both using BigDecimal, the value should be parsed in same mechanism.
            if (maximum.toLowerCase().equals(value.toLowerCase()) || Double.valueOf(maximum).equals(Double.POSITIVE_INFINITY)) {
                assertTrue(messages3.isEmpty(), format("Maximum %s and value %s are equal, thus no schema violation should be reported", maximum, value));
            } else {
                assertFalse(messages3.isEmpty(), format("Maximum %s is smaller than value %s ,  should be validation error reported", maximum, value));
            }
        }
    }

}