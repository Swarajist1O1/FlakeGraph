class DummyClass_110920 {
    @Test(expected = NullPointerException.class)
    public void nullMapFromCursor() {
        SQLiteTypeMapping.builder()
                .putResolver(mock(PutResolver.class))
                .getResolver(null)
                .deleteResolver(mock(DeleteResolver.class))
                .build();
    }

}