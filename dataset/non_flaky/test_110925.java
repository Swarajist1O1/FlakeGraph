class DummyClass_110925 {
    @Test
    public void nullColumnHackShouldBeNullByDefault() {
        InsertQuery insertQuery = InsertQuery.builder()
                .table("test_table")
                .build();

        assertThat(insertQuery.nullColumnHack()).isNull();
    }

}