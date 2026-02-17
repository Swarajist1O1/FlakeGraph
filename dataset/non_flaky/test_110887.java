class DummyClass_110887 {
    @Test
    public void getNumberOfResultsWithQuery() {
        storIOSQLite.get()
                .numberOfResults()
                .withQuery(Query.builder()
                        .table(TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}