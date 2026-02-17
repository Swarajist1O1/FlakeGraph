class DummyClass_98441 {
  @Test
  public void requestSampleUnitsHappyPath() throws CTPException {
    UUID collexId = UUID.randomUUID();
    UUID sampleSummaryId = UUID.randomUUID();
    CollectionExercise collectionExercise = new CollectionExercise();
    collectionExercise.setId(collexId);
    SampleLink sampleLink = new SampleLink();
    sampleLink.setSampleSummaryId(sampleSummaryId);
    List<SampleLink> sampleLinks = Collections.singletonList(sampleLink);
    SampleUnitsRequestDTO sampleUnitsRequestDTO = new SampleUnitsRequestDTO();
    sampleUnitsRequestDTO.setSampleUnitsTotal(666);

    // Given
    when(collectRepo.findOneById(eq(collexId))).thenReturn(collectionExercise);
    when(sampleLinkRepo.findByCollectionExerciseId(any())).thenReturn(sampleLinks);
    when(sampleSvcClient.getSampleUnitCount(any())).thenReturn(sampleUnitsRequestDTO);
    when(sampleSvcClient.requestSampleUnits(any())).thenReturn(sampleUnitsRequestDTO);

    // When
    underTest.requestSampleUnits(collexId);

    // Then
    verify(collexSampleUnitReceiptPreparer).prepareCollexToAcceptSampleUnits(eq(collexId), eq(666));
    verify(partySvcClient).linkSampleSummaryId(any(), any());
  }

}