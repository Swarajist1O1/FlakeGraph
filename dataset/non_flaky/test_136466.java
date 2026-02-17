class DummyClass_136466 {
    @Test
    public void should_search_numeric_gte() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Gte(138.47f)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getNumeric()).isEqualTo(138.47f);
    }

}