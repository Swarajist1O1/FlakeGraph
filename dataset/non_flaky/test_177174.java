class DummyClass_177174 {
    @Test
    public void expectSuccessWithFile() throws Exception {
        final File file = folder.newFile();

        assertThat(file.length()).isZero();

        final KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);
        keyStore.store(new FileOutputStream(file), "".toCharArray());

        assertThat(file.length()).isGreaterThan(0);
        assertThat(file.canRead()).isTrue();
        assertThat(file.exists()).isTrue();

        new KeyStoreCredentialResolverBuilder(file).build();
    }

}