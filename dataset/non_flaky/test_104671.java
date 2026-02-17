class DummyClass_104671 {
  @Test
  public void testRealtimeToOfflineSegmentsTask()
      throws IOException {
    List<SegmentZKMetadata> segmentsZKMetadata = _pinotHelixResourceManager.getSegmentsZKMetadata(_offlineTableName);
    Assert.assertTrue(segmentsZKMetadata.isEmpty());

    long expectedWatermark = _dataSmallestTimeMs + 86400000;
    int numOfflineSegments = 0;
    for (int i = 0; i < 3; i++) {
      // Schedule task
      Assert.assertNotNull(_taskManager.scheduleTasks().get(MinionConstants.RealtimeToOfflineSegmentsTask.TASK_TYPE));
      Assert.assertTrue(_helixTaskResourceManager.getTaskQueues().contains(
          PinotHelixTaskResourceManager.getHelixJobQueueName(MinionConstants.RealtimeToOfflineSegmentsTask.TASK_TYPE)));
      // Should not generate more tasks
      Assert.assertNull(_taskManager.scheduleTasks().get(MinionConstants.RealtimeToOfflineSegmentsTask.TASK_TYPE));

      // Wait at most 600 seconds for all tasks COMPLETED
      waitForTaskToComplete(expectedWatermark);
      // check segment is in offline
      segmentsZKMetadata = _pinotHelixResourceManager.getSegmentsZKMetadata(_offlineTableName);
      numOfflineSegments++;
      Assert.assertEquals(segmentsZKMetadata.size(), numOfflineSegments);
      long expectedOfflineSegmentTimeMs = expectedWatermark - 86400000;
      Assert.assertEquals(segmentsZKMetadata.get(i).getStartTimeMs(), expectedOfflineSegmentTimeMs);
      Assert.assertEquals(segmentsZKMetadata.get(i).getEndTimeMs(), expectedOfflineSegmentTimeMs);

      expectedWatermark += 86400000;
    }
    testHardcodedSqlQueries();

    // Delete the table
    dropRealtimeTable(_realtimeTableName);

    // Check if the metadata is cleaned up on table deletion
    verifyTableDelete(_realtimeTableName);
  }

}