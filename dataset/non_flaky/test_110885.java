class DummyClass_110885 {
    @Test
    public void getListOfObjectsWithQuery() {
        storIOSQLite.get()
                .listOfObjects(Tweet.class)
                .withQuery(Query.builder()
                        .table(TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}