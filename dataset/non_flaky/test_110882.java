class DummyClass_110882 {
    @Test
    public void getCursorWithRawQuery() {
        storIOSQLite.get()
                .cursor()
                .withQuery(RawQuery.builder()
                        .query("select * from " + TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}