class DummyClass_92684 {
    @Test
    public void testIothreads() throws Exception {
        ProviderConfig provider = new ProviderConfig();
        provider.setIothreads(10);
        assertThat(provider.getIothreads(), is(10));
    }

}