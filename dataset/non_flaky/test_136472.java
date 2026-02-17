class DummyClass_136472 {
    @Test
    public void should_search_numeric_gte_and_lte() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Gte_And_Lte(87.39f, 138.47f)
                .getList();

        //Then
        assertThat(actual).hasSize(3);
        assertThat(actual.stream().map(EntityWithDSESearch::getNumeric).collect(toList()))
                .contains(87.39f, 100.03f, 138.47f);
    }

}