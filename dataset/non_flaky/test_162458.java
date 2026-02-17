class DummyClass_162458 {
    @Test
    public void valueIsEscapedTest() throws Exception {
        ImmutableMap<String, String> pairs = ImmutableMap.<String, String>builder()
                .put("1", "value with spaces")
                .put("2", "value\nwith\nnewlines")
                .put("3", "value\twith\ttab")
                .build();

        assertStatement(new KeyValuesStatement("TEST", pairs));
    }

}