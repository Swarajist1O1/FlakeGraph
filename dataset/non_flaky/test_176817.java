class DummyClass_176817 {
    @Test
    public void formatShouldEscapeWhitespaces() throws Exception {
        LoadRunnerTransaction trx = new LoadRunnerTransaction() {{
            setName("hello world");
            setTrxFlag("LR_AUTO");
        }};
        assertThat(trx.format(), containsString("hello_world"));
    }

}