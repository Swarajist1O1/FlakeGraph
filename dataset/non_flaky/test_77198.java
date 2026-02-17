class DummyClass_77198 {
    @Test
    public void positiveExclusiveInteger() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            maximum,                       value
                {"9223372036854775000", "9223372036854774988"},
                {"20", "10"},

//                threshold outside long range
                {"9223372036854775809", "9223372036854775806"},

//                both threshold and value are outside long range
                {"9223372036854775809", "9223372036854775808"},
        });

        expectNoMessages(values, EXCLUSIVE_INTEGER);

        expectNoMessages(values, EXCLUSIVE_INTEGER, bigIntegerMapper);
    }

}