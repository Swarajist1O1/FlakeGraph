class DummyClass_156079 {
    @Test
    public void testAddDouble() {
        builder.addScopes(RedditScope.EDIT, RedditScope.EDIT);
        builder.addScope(RedditScope.EDIT);
        assertEquals(RedditScope.EDIT.value(), builder.build());
        builder.removeScope(RedditScope.EDIT);
        assertEquals("", builder.build());
    }

}