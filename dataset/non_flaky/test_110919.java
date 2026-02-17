class DummyClass_110919 {
    @Test(expected = NullPointerException.class)
    public void nullPutResolver() {
        SQLiteTypeMapping.builder()
                .putResolver(null)
                .getResolver(mock(GetResolver.class))
                .deleteResolver(mock(DeleteResolver.class))
                .build();
    }

}