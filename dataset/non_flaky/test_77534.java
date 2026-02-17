class DummyClass_77534 {
    @Test
    public void testResource() {
        assertThat(resourceTestRule.target("test").request()
                .get(String.class))
                .isEqualTo("test");
    }

}