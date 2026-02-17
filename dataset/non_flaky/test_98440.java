class DummyClass_98440 {
  @Test
  public void testAcceptSampleUnitAlreadyExists() throws CTPException {
    CollectionExercise collex = new CollectionExercise();
    collex.setId(COLLEX_ID);
    collex.setSampleSize(99);
    collex.setState(CollectionExerciseState.EXECUTION_STARTED);

    SampleUnit sampleUnit = new SampleUnit();
    sampleUnit.setCollectionExerciseId(COLLEX_ID.toString());
    sampleUnit.setFormType("X");
    sampleUnit.setId(SAMPLE_ID.toString());
    sampleUnit.setSampleUnitType("B");
    sampleUnit.setSampleUnitRef("REF123");
    when(collectRepo.findOneById(any())).thenReturn(collex);
    when(sampleUnitRepo.existsBySampleUnitRefAndSampleUnitTypeAndSampleUnitGroupCollectionExercise(
            any(), any(), any()))
        .thenReturn(true);

    underTest.acceptSampleUnit(sampleUnit);

    verify(collectionExerciseTransitionState, never()).transition(any(), any());
    verify(sampleUnitGroupRepo, never()).saveAndFlush(any());
    verify(sampleUnitRepo, never()).saveAndFlush(any());
    verify(collectRepo, never()).saveAndFlush(any());
  }

}