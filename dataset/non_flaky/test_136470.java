class DummyClass_136470 {
    @Test
    public void should_search_numeric_gt_and_lte() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Gt_And_Lte(87.39f, 138.47f)
                .getList();

        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual.stream().map(EntityWithDSESearch::getNumeric).collect(toList()))
                .contains(100.03f, 138.47f);
    }

}