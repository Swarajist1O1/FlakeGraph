class DummyClass_57213 {
  @Test
  public void testBadBehavedTaskIsIgnored() throws Exception {
    String taskName = "Dummy_" + System.currentTimeMillis();
    DummyReconDBTask dummyReconDBTask =
        new DummyReconDBTask(taskName, DummyReconDBTask.TaskType.ALWAYS_FAIL);
    reconTaskController.registerTask(dummyReconDBTask);

    OMUpdateEventBatch omUpdateEventBatchMock = mock(OMUpdateEventBatch.class);
    when(omUpdateEventBatchMock.isEmpty()).thenReturn(false);
    when(omUpdateEventBatchMock.getLastSequenceNumber()).thenReturn(100L);

    OMMetadataManager omMetadataManagerMock = mock(OMMetadataManager.class);
    for (int i = 0; i < 2; i++) {
      reconTaskController.consumeOMEvents(omUpdateEventBatchMock,
          omMetadataManagerMock);

      assertFalse(reconTaskController.getRegisteredTasks().isEmpty());
      assertEquals(dummyReconDBTask, reconTaskController.getRegisteredTasks()
          .get(dummyReconDBTask.getTaskName()));
    }

    //Should be ignored now.
    reconTaskController.consumeOMEvents(omUpdateEventBatchMock,
        omMetadataManagerMock);
    assertTrue(reconTaskController.getRegisteredTasks().isEmpty());

    reconTaskStatusDao = getDao(ReconTaskStatusDao.class);
    ReconTaskStatus dbRecord = reconTaskStatusDao.findById(taskName);

    Assert.assertEquals(taskName, dbRecord.getTaskName());
    Assert.assertEquals(Long.valueOf(0L), dbRecord.getLastUpdatedTimestamp());
    Assert.assertEquals(Long.valueOf(0L), dbRecord.getLastUpdatedSeqNumber());
  }

}