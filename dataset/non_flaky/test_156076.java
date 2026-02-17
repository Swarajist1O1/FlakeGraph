class DummyClass_156076 {
    @Test
    public void testAddRemove() {
        builder.addScope(RedditScope.EDIT);
        builder.removeScope(RedditScope.EDIT);
        assertEquals("", builder.build());
    }

}