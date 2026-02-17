class DummyClass_77548 {
    @Test
    public void testCustomClientConfiguration() {
        assertThat(resourceTestRule.client().getConfiguration().isRegistered(DummyExceptionMapper.class)).isTrue();
    }

}