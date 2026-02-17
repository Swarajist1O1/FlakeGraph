class DummyClass_77204 {
    @Test
    public void positiveNumber() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            minimum,                       value
                {"1000", "1000.1"},
        });

        expectNoMessages(values, NUMBER, mapper);
    }

}