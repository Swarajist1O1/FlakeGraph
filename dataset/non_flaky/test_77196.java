class DummyClass_77196 {
    @Test
    public void positiveInteger() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            maximum,                       value
                {"9223372036854775807", "9223372036854775807"},
                {"9223372036854775808", "9223372036854775808"},

//                testIntegerTypeWithFloatMaxPositive
                {"37.7", "37"},

//                testMaximumDoubleValue
                {"1E39", "1000"},
        });

        expectNoMessages(values, INTEGER);

        expectNoMessages(values, INTEGER, bigIntegerMapper);
    }

}