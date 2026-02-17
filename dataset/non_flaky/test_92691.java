class DummyClass_92691 {
    @Test
    public void testClient() throws Exception {
        ProviderConfig provider = new ProviderConfig();
        provider.setClient("client");
        assertThat(provider.getClient(), equalTo("client"));
    }

}