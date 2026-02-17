class DummyClass_110921 {
    @Test(expected = NullPointerException.class)
    public void nullMapToDeleteQuery() {
        SQLiteTypeMapping.builder()
                .putResolver(mock(PutResolver.class))
                .getResolver(mock(GetResolver.class))
                .deleteResolver(null)
                .build();
    }

}