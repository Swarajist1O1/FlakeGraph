class DummyClass_92686 {
    @Test
    public void testAccepts() throws Exception {
        ProviderConfig provider = new ProviderConfig();
        provider.setAccepts(10);
        assertThat(provider.getAccepts(), is(10));
    }

}