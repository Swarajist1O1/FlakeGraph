class DummyClass_136471 {
    @Test
    public void should_search_numeric_gte_and_lt() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Gte_And_Lt(87.39f, 138.47f)
                .getList();

        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual.stream().map(EntityWithDSESearch::getNumeric).collect(toList()))
                .contains(87.39f, 100.03f);
    }

}