class DummyClass_136483 {
    @Test
    public void should_search_using_raw_predicates() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .string().RawPredicate("*eed?u*")
                .numeric().RawPredicate("{100 TO 150}")
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getString()).isEqualTo("speedrun");
    }

}