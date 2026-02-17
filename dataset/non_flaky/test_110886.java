class DummyClass_110886 {
    @Test
    public void getNumberOfResultsWithRawQuery() {
        storIOSQLite.get()
                .numberOfResults()
                .withQuery(RawQuery.builder()
                        .query("select * from " + TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}