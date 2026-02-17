class DummyClass_110905 {
    @Test
    public void shouldReturnQueryInGetData() {
        final RawQuery query = RawQuery.builder()
                .query("DROP TABLE IF EXISTS no_such_table") // we don't want to really delete table
                .build();
        final PreparedExecuteSQL operation = storIOSQLite
                .executeSQL()
                .withQuery(query)
                .prepare();

        assertThat(operation.getData()).isEqualTo(query);
    }

}