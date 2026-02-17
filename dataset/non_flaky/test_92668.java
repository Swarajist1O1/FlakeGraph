class DummyClass_92668 {
    @Test
    public void testOrganization() throws Exception {
        ModuleConfig module = new ModuleConfig();
        module.setOrganization("org");
        assertThat(module.getOrganization(), equalTo("org"));
    }

}