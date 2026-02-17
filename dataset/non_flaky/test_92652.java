class DummyClass_92652 {
    @Test
    public void testSession() throws Exception {
        RegistryConfig registry = new RegistryConfig();
        registry.setSession(10);
        assertThat(registry.getSession(), is(10));
    }

}