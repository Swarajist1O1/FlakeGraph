class DummyClass_57211 {
  @Test
  public void testConsumeOMEvents() throws Exception {
    ReconOmTask reconOmTaskMock = getMockTask("MockTask");
    when(reconOmTaskMock.process(any(OMUpdateEventBatch.class)))
        .thenReturn(new ImmutablePair<>("MockTask", true));
    reconTaskController.registerTask(reconOmTaskMock);
    OMUpdateEventBatch omUpdateEventBatchMock = mock(OMUpdateEventBatch.class);
    when(omUpdateEventBatchMock.getLastSequenceNumber()).thenReturn(100L);
    when(omUpdateEventBatchMock.isEmpty()).thenReturn(false);

    long startTime = System.currentTimeMillis();
    reconTaskController.consumeOMEvents(
        omUpdateEventBatchMock,
        mock(OMMetadataManager.class));

    verify(reconOmTaskMock, times(1))
        .process(any());
    long endTime = System.currentTimeMillis();

    reconTaskStatusDao = getDao(ReconTaskStatusDao.class);
    ReconTaskStatus reconTaskStatus = reconTaskStatusDao.findById("MockTask");
    long taskTimeStamp = reconTaskStatus.getLastUpdatedTimestamp();
    long seqNumber = reconTaskStatus.getLastUpdatedSeqNumber();

    Assert.assertTrue(startTime <= taskTimeStamp
        && taskTimeStamp <= endTime);
    Assert.assertEquals(seqNumber,
        omUpdateEventBatchMock.getLastSequenceNumber());
  }

}