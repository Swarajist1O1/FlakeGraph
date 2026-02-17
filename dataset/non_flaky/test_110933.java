class DummyClass_110933 {
    @Test
    public void buildWithNormalValues() {
        final String table = "test_table";
        final String nullColumnHack = "test_null_column_hack";
        final Set<String> tags = singleton("tag");

        final InsertQuery insertQuery = InsertQuery.builder()
                .table(table)
                .nullColumnHack(nullColumnHack)
                .affectsTags(tags)
                .build();

        assertThat(insertQuery.table()).isEqualTo(table);
        assertThat(insertQuery.nullColumnHack()).isEqualTo(nullColumnHack);
        assertThat(insertQuery.affectsTags()).isEqualTo(tags);
    }

}