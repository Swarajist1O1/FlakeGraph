class DummyClass_136484 {
    @Test
    public void should_search_using_raw_solr_query() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .rawSolrQuery("string:*eed?u* OR numeric:[100 TO *]")
                .getList();

        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual.stream()
                .map(EntityWithDSESearch::getString)
                .collect(toList()))
                .contains("speedrun","speedster");
    }

}