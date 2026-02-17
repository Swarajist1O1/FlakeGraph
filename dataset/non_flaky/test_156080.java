class DummyClass_156080 {
    @Test
    public void testAddMultiple() {
        builder.addScopes(RedditScope.EDIT, RedditScope.FLAIR);
        assertTrue(
                (RedditScope.EDIT.value() + RedditScope.SEPARATOR + RedditScope.FLAIR.value()).equals(builder.build()) 
                ||
                (RedditScope.FLAIR.value() + RedditScope.SEPARATOR + RedditScope.EDIT.value()).equals(builder.build()) 
                );
        builder.removeScopes(RedditScope.EDIT, RedditScope.FLAIR);
    }

}