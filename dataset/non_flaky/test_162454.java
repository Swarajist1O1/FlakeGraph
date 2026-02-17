class DummyClass_162454 {
    @Test
    public void multilineTest() throws Exception {
        ImmutableMap<String, String> pairs = ImmutableMap.<String, String>builder()
                .put("line1", "1")
                .put("line2", "2")
                .put("line3", "3")
                .build();

        assertStatement(new KeyValuesStatement("TEST", pairs));
    }

}