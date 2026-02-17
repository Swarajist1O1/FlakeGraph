class DummyClass_136478 {
    @Test
    public void should_search_date_gt_and_lt() throws Exception {
        //Given
        final Date searchedDate1 = toDate("2016-09-25 13:00:00.000Z");
        final Date searchedDate2 = toDate("2016-09-26 09:00:00.000Z");

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .date().Gt_And_Lt(searchedDate1, searchedDate2)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.stream()
                .map(EntityWithDSESearch::getDate)
                .map(this::toString)
                .collect(toList()))
                .contains("2016-09-26 08:00:00.000Z");
    }

}