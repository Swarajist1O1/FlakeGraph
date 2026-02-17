class DummyClass_92657 {
    @Test
    public void testGroup() throws Exception {
        RegistryConfig registry = new RegistryConfig();
        registry.setGroup("group");
        assertThat(registry.getGroup(), equalTo("group"));
    }

}