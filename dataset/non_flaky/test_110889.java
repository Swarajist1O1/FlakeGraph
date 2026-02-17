class DummyClass_110889 {
    @Test
    public void getObjectWithQuery() {
        storIOSQLite.get()
                .object(Tweet.class)
                .withQuery(Query.builder()
                        .table(TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}