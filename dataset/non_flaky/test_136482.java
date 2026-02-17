class DummyClass_136482 {
    @Test
    public void should_search_using_multiple_predicates() throws Exception {
        //Given
        final Date searchedDate1 = toDate("2016-09-25 13:00:00.000Z");
        final Date searchedDate2 = toDate("2016-09-26 09:00:00.000Z");

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .date().Gte_And_Lte(searchedDate1, searchedDate2)
                .string().Contains("run")
                .numeric().Gt_And_Lte(80f, 110f)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getNumeric()).isEqualTo(87.39f);
    }

}