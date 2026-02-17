class DummyClass_110878 {
    @Test
    public void deleteByQuery() {
        storIOSQLite.delete()
                .byQuery(DeleteQuery.builder()
                        .table(TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}