class DummyClass_136486 {
    @Test
    public void should_select_with_allow_per_partition_limit() throws Exception {
        //Given
        Long id1 = RandomUtils.nextLong(0L, Long.MAX_VALUE);
        Long id2 = RandomUtils.nextLong(0L, Long.MAX_VALUE);
        Long id3 = RandomUtils.nextLong(0L, Long.MAX_VALUE);

        scriptExecutor.executeScriptTemplate("EntityWithClustering/insertRows.cql", ImmutableMap.of("id1", id1, "id2", id2, "id3", id3));

        //When
        final List<EntityWithClustering> list = manager
                .dsl()
                .select()
                .allColumns_FromBaseTable()
                .without_WHERE_Clause()
                .perPartitionLimit(2)
                .getList();

        //Then
        assertThat(list).hasSize(6);
        assertThat(list.stream().map(x -> x.getClust()).collect(Collectors.toList())).containsExactly(1L, 2L, 1L, 2L, 1L, 2L);
    }

}