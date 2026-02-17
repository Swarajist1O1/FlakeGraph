class DummyClass_77530 {
    @Test
    public void supportsSuppliedConfigAttributeOverrides() throws Exception {
        assertThat(System.getProperty("app-rule.extra")).isEqualTo("supplied");
        assertThat(System.getProperty("dw.extra")).isEqualTo("supplied again");
    }

}