class DummyClass_177162 {
    @Test
    public void shouldMatchJWTPattern() throws UnsupportedEncodingException {
        final Pattern p = Pattern.compile("[a-zA-Z0-9-_]+\\.[a-zA-Z0-9-_]+\\.[a-zA-Z0-9-_]+");
        final SamlRequestIdManager manager =
                SamlRequestIdManager.ofJwt("me", "test", 60, 5);
        final String id = manager.newId();
        assertThat(p.matcher(id).matches()).isTrue();
        assertThat(manager.validateId(id)).isTrue();
    }

}