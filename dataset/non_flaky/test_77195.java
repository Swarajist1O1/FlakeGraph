class DummyClass_77195 {
    @Test
    public void negativeNumber() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            maximum,                           value
//            These values overflow 64bit IEEE 754
                {"1.7976931348623157e+308", "1.7976931348623159e+308"},
                {"1.7976931348623156e+308", "1.7976931348623157e+308"},

//            Here, threshold is parsed as integral number, yet payload is 'number'
                {"1000", "1000.1"},

//          See a {@link #doubleValueCoarsing() doubleValueCoarsing} test notes below
//            {"1.7976931348623157e+308",         "1.7976931348623158e+308"},
        });

        expectSomeMessages(values, NUMBER);

        expectSomeMessages(values, NUMBER, mapper, bigDecimalMapper);

        expectSomeMessages(values, NUMBER, bigDecimalMapper, bigDecimalMapper);
    }

}