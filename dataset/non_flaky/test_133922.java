class DummyClass_133922 {
    @Test
    public void testGetCrumb() {
        final Crumb crumb = api().crumb();
        assertNotNull(crumb);
        assertNotNull(crumb.value());
        assertTrue(crumb.errors().isEmpty());
    }

}