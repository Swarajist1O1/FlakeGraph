class DummyClass_177165 {
    @Test
    public void shouldFail() {
        assertThatThrownBy(() -> SamlRequestIdManager.ofJwt("me", "test", 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SamlRequestIdManager.ofJwt("me", "test", -1, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> SamlRequestIdManager.ofJwt("me", "test", 1, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}