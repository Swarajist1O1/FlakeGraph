class DummyClass_77194 {
    @Test
    public void positiveNumber() throws IOException {
        String[][] values = augmentWithQuotes(new String[][]{
//            maximum,                       value
                {"1000.1", "1000"},
                {"1000", "1E3"},
        });

        expectNoMessages(values, NUMBER);

    }

}