class DummyClass_77533 {
    @Test
    public void testResource() {
        assertThat(resourceTestRule.target("test").request()
                .get(String.class))
                .isEqualTo("Default message");
    }

}