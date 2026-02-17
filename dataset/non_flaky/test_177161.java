class DummyClass_177161 {
    @Test
    public void shouldBeDifferentToEachOther() throws UnsupportedEncodingException {
        final SamlRequestIdManager manager =
                SamlRequestIdManager.ofJwt("me", "test", 60, 5);

        final String id1 = manager.newId();
        final String id2 = manager.newId();
        final String id3 = manager.newId();

        assertThat(id1).isNotEqualTo(id2).isNotEqualTo(id3);
        assertThat(id2).isNotEqualTo(id3);
    }

}