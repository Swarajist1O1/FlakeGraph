class DummyClass_110906 {
    @Test
    public void execSQLWithEmptyArgs() {
        // Should not throw exceptions!
        storIOSQLite
                .executeSQL()
                .withQuery(RawQuery.builder()
                        .query("DROP TABLE IF EXISTS no_such_table") // we don't want to really delete table
                        .build())
                .prepare()
                .executeAsBlocking();
    }

}