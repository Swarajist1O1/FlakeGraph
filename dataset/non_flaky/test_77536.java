class DummyClass_77536 {
    @Test
    public void testClientSupportsPatchMethod() {
        final String resp = resourceTestRule.target("test")
            .request()
            .method("PATCH", Entity.text("Patch is working"), String.class);
        assertThat(resp).isEqualTo("Patch is working");
    }

}