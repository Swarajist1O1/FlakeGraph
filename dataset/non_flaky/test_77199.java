class DummyClass_77199 {
    @Test
    public void negativeExclusiveInteger() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            maximum,                       value
                {"10", "20"},

//                value outside long range
                {"9223372036854775806", "9223372036854775808"},

//                both threshold and value are outside long range
                {"9223372036854775808", "9223372036854775809"},
        });

        expectSomeMessages(values, EXCLUSIVE_INTEGER);

        expectSomeMessages(values, EXCLUSIVE_INTEGER, mapper, bigIntegerMapper);
    }

}