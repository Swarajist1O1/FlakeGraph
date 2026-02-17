class DummyClass_110888 {
    @Test
    public void getObjectWithRawQuery() {
        storIOSQLite.get()
                .object(Tweet.class)
                .withQuery(RawQuery.builder()
                        .query("select * from " + TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}