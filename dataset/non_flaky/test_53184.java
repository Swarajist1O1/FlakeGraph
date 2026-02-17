class DummyClass_53184 {
    @Test
    public void testDefaultConfig() {
        config = new GoogleMfaProviderConfig();
        assertThat(config.getProviderDescription(), is(nullValue()));
        assertThat(config.getIssuer(), is(nullValue()));
    }

}