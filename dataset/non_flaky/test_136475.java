class DummyClass_136475 {
    @Test
    public void should_search_date_gte() throws Exception {
        //Given
        final Date searchedDate = toDate("2016-09-26 08:00:00.000Z");

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .date().Gte(searchedDate)
                .getList();

        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual.stream()
                .map(EntityWithDSESearch::getDate)
                .map(this::toString)
                .collect(toList()))
            .contains("2016-09-26 08:00:00.000Z", "2016-09-26 09:00:00.000Z");
    }

}