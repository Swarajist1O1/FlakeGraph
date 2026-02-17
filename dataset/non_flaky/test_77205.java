class DummyClass_77205 {
    @Test
    public void negativeNumber() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            minimum,                           value
                {"-1.7976931348623157e+308", "-1.7976931348623159e+308"},
                {"-1.7976931348623156e+308", "-1.7976931348623157e+308"},
                {"-1000", "-1E309"},
                {"1000.1", "1000"},
//          See a {@link #doubleValueCoarsing() doubleValueCoarsing} test notes below
//            {"-1.7976931348623157e+308",         "-1.7976931348623158e+308"},
        });

        expectSomeMessages(values, NUMBER, mapper, mapper);

        expectSomeMessages(values, NUMBER, mapper, bigDecimalMapper);

        expectSomeMessages(values, NUMBER, bigDecimalMapper, bigDecimalMapper);
    }

}