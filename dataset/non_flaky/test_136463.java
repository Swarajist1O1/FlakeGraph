class DummyClass_136463 {
    @Test
    public void should_search_text_using_substring() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .string().Contains("eds")
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.stream().map(EntityWithDSESearch::getString).collect(toList()))
                .contains("speedster");
    }

}