class DummyClass_136468 {
    @Test
    public void should_search_numeric_lte() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Lte(87.39f)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getNumeric()).isEqualTo(87.39f);
    }

}