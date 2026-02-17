class DummyClass_110928 {
    @Test
    public void completeBuilderShouldUpdateTable() {
        InsertQuery query = InsertQuery.builder()
                .table("old_table")
                .table("new_table")
                .build();

        assertThat(query.table()).isEqualTo("new_table");
    }

}