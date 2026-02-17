class DummyClass_57214 {
  @Test
  public void testReInitializeTasks() throws Exception {

    ReconOMMetadataManager omMetadataManagerMock = mock(
        ReconOMMetadataManager.class);
    ReconOmTask reconOmTaskMock =
        getMockTask("MockTask2");
    when(reconOmTaskMock.reprocess(omMetadataManagerMock))
        .thenReturn(new ImmutablePair<>("MockTask2", true));
    when(omMetadataManagerMock.getLastSequenceNumberFromDB()
    ).thenReturn(100L);

    long startTime = System.currentTimeMillis();
    reconTaskController.registerTask(reconOmTaskMock);
    reconTaskController.reInitializeTasks(omMetadataManagerMock);
    long endTime = System.currentTimeMillis();

    verify(reconOmTaskMock, times(1))
        .reprocess(omMetadataManagerMock);

    verify(omMetadataManagerMock, times(1)
    ).getLastSequenceNumberFromDB();

    ReconTaskStatus reconTaskStatus = reconTaskStatusDao.findById("MockTask2");
    long taskTimeStamp = reconTaskStatus.getLastUpdatedTimestamp();
    long seqNumber = reconTaskStatus.getLastUpdatedSeqNumber();

    Assert.assertTrue(startTime <= taskTimeStamp
        && taskTimeStamp <= endTime);
    Assert.assertEquals(seqNumber,
        omMetadataManagerMock.getLastSequenceNumberFromDB());
  }

}