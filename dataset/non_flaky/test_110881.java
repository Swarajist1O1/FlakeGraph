class DummyClass_110881 {
    @Test
    public void execSql() {
        storIOSQLite.executeSQL()
                .withQuery(RawQuery.builder()
                        .query("select * from " + TweetTableMeta.TABLE)
                        .build()
                )
                .prepare()
                .executeAsBlocking();
        checkInterceptorsCalls();
    }

}