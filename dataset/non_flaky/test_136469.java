class DummyClass_136469 {
    @Test
    public void should_search_numeric_gt_and_lt() throws Exception {
        //Given

        //When
        final List<EntityWithDSESearch> actual = manager
                .indexed()
                .select()
                .allColumns_FromBaseTable()
                .where()
                .numeric().Gt_And_Lt(87.39f, 138.47f)
                .getList();

        //Then
        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getNumeric()).isEqualTo(100.03f);
    }

}