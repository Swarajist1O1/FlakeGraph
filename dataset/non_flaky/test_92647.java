class DummyClass_92647 {
    @Test
    public void testCheck() throws Exception {
        RegistryConfig registry = new RegistryConfig();
        registry.setCheck(true);
        assertThat(registry.isCheck(), is(true));
    }

}