class DummyClass_77197 {
    @Test
    public void negativeInteger() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            maximum,                value
                {"9223372036854775800", "9223372036854775855"},
                {"9223372036854775807", "9223372036854775808"},
                {"9223372036854775807", new BigDecimal(String.valueOf(Double.MAX_VALUE)).add(BigDecimal.ONE).toString()},
                {"9223372036854775806", new BigDecimal(String.valueOf(Double.MAX_VALUE)).add(BigDecimal.ONE).toString()},
                {"9223372036854776000", "9223372036854776001"},
                {"1000", "1E39"},
                {"37.7", "38"},
        });

        expectSomeMessages(values, INTEGER);

        expectSomeMessages(values, INTEGER, mapper, bigIntegerMapper);
    }

}