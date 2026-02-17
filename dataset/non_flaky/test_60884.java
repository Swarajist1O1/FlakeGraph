class DummyClass_60884 {
  @Test
  public void testSuspendedDoesntRun()
  {
    MaterializedViewSupervisorSpec suspended = new MaterializedViewSupervisorSpec(
        "base",
        new DimensionsSpec(Collections.singletonList(new StringDimensionSchema("dim")), null, null),
        new AggregatorFactory[]{new LongSumAggregatorFactory("m1", "m1")},
        HadoopTuningConfig.makeDefaultTuningConfig(),
        null,
        null,
        null,
        null,
        null,
        true,
        objectMapper,
        taskMaster,
        taskStorage,
        metadataSupervisorManager,
        sqlSegmentsMetadataManager,
        indexerMetadataStorageCoordinator,
        new MaterializedViewTaskConfig(),
        EasyMock.createMock(AuthorizerMapper.class),
        EasyMock.createMock(ChatHandlerProvider.class),
        new SupervisorStateManagerConfig()
    );
    MaterializedViewSupervisor supervisor = (MaterializedViewSupervisor) suspended.createSupervisor();

    // mock IndexerSQLMetadataStorageCoordinator to ensure that retrieveDataSourceMetadata is not called
    // which will be true if truly suspended, since this is the first operation of the 'run' method otherwise
    IndexerSQLMetadataStorageCoordinator mock = EasyMock.createMock(IndexerSQLMetadataStorageCoordinator.class);
    EasyMock.expect(mock.retrieveDataSourceMetadata(suspended.getDataSourceName()))
            .andAnswer(() -> {
              Assert.fail();
              return null;
            })
            .anyTimes();

    EasyMock.replay(mock);
    supervisor.run();
  }

}