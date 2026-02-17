class DummyClass_177175 {
    @Test
    public void expectSuccessWithResource() throws Exception {
        new KeyStoreCredentialResolverBuilder(getClass().getClassLoader(), "keystore/test.jks").build();
    }

}