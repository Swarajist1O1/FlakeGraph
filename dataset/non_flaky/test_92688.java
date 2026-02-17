class DummyClass_92688 {
    @Test
    public void testPayload() throws Exception {
        ProviderConfig provider = new ProviderConfig();
        provider.setPayload(10);
        assertThat(provider.getPayload(), is(10));
    }

}