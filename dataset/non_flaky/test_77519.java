class DummyClass_77519 {
    @Test
    public void returnsConfiguration() {
        final TestConfiguration config = RULE.getConfiguration();
        assertThat(config.getMessage()).isEqualTo("Yes, it's here");
    }

}