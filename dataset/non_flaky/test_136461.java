class DummyClass_136461 {
    @Test
    public void should_search_text_using_prefix() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .string().StartWith("speed")
                .getList();

        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual.stream().map(EntityWithDSESearch::getString).collect(toList()))
                .contains("speedster", "speedrun");
    }

}