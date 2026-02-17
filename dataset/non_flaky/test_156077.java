class DummyClass_156077 {
    @Test
    public void testAddRemoveMultiple() {
        builder.addScopes(RedditScope.EDIT, RedditScope.MODPOSTS);
        builder.removeScopes(RedditScope.EDIT, RedditScope.MODPOSTS, RedditScope.MODCONFIG);
        assertEquals("", builder.build());
    }

}