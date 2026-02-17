class DummyClass_176828 {
    @Test
    public void read() throws Exception {
        Map<String, Attributes> entities = LDIFUtils.read(getClass().getResourceAsStream("/example.ldif"));
        assertThat(entities.size(), is(4));
    }

}