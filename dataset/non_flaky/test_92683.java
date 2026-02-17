class DummyClass_92683 {
    @Test
    public void testThreads() throws Exception {
        ProviderConfig provider = new ProviderConfig();
        provider.setThreads(10);
        assertThat(provider.getThreads(), is(10));
    }

}