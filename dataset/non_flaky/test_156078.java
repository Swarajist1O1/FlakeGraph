class DummyClass_156078 {
    @Test
    public void testAdd() {
        builder.addScope(RedditScope.EDIT);
        assertEquals(RedditScope.EDIT.value(), builder.build());
        builder.removeScope(RedditScope.EDIT);
    }

}