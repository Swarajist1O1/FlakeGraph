class DummyClass_110883 {
    @Test
    public void getCursorWithQuery() {
        storIOSQLite.get()
                .cursor()
                .withQuery(Query.builder()
                        .table(TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}