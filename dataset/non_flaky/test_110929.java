class DummyClass_110929 {
    @Test
    public void createdThroughToBuilderQueryShouldBeEqual() {
        final String table = "test_table";
        final String nullColumnHack = "test_null_column_hack";
        final String tag = "test_tag";

        final InsertQuery firstQuery = InsertQuery.builder()
                .table(table)
                .nullColumnHack(nullColumnHack)
                .affectsTags(tag)
                .build();

        final InsertQuery secondQuery = firstQuery.toBuilder().build();

        assertThat(secondQuery).isEqualTo(firstQuery);
    }

}