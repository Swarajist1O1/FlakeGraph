class DummyClass_136462 {
    @Test
    public void should_search_text_using_suffix() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .string().EndWith("run")
                .getList();

        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual.stream().map(EntityWithDSESearch::getString).collect(toList()))
                .contains("long run", "speedrun");
    }

}