class DummyClass_177176 {
    @Test
    public void expectNotFound() throws Exception {
        assertThatThrownBy(
                () -> new KeyStoreCredentialResolverBuilder(new File("/not_exist")).build())
                .isInstanceOf(FileNotFoundException.class);
        assertThatThrownBy(
                () -> new KeyStoreCredentialResolverBuilder(getClass().getClassLoader(), "not_exist").build())
                .isInstanceOf(FileNotFoundException.class)
                .hasMessageContaining("Resource not found");
    }

}