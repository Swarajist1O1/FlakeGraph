class DummyClass_110884 {
    @Test
    public void getListOfObjectsWithRawQuery() {
        storIOSQLite.get()
                .listOfObjects(Tweet.class)
                .withQuery(RawQuery.builder()
                        .query("select * from " + TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}