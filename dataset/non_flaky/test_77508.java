class DummyClass_77508 {
    @Test
    public void hasAWorkingEqualsMethod() {
        assertThat(credentials)
            .isEqualTo(credentials)
            .isEqualTo(new BasicCredentials("u", "p"))
            .isNotEqualTo(null)
            .isNotEqualTo("string")
            .isNotEqualTo(new BasicCredentials("u1", "p"))
            .isNotEqualTo(new BasicCredentials("u", "p1"));
    }

}