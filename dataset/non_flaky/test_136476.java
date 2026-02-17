class DummyClass_136476 {
    @Test
    public void should_search_date_lt() throws Exception {
        //Given
        final Date searchedDate = toDate("2016-09-26 08:00:00.000Z");

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .date().Lt(searchedDate)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(toString(actual.get(0).getDate())).isEqualTo("2016-09-25 13:00:00.000Z");
    }

}