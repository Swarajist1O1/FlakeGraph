class DummyClass_136464 {
    @Test
    public void should_search_numeric_eq() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Eq(100.03f)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getNumeric()).isEqualTo(100.03f);
    }

}