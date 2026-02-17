class DummyClass_104626 {
  @Test
  public void testSingleLevelConcat()
      throws Exception {
    // The original segments are time partitioned by month:
    // segmentName (totalDocs)
    // myTable1_16071_16101_3 (9746)
    // myTable1_16102_16129_4 (8690)
    // myTable1_16130_16159_5 (9621)
    // myTable1_16160_16189_6 (9454)
    // myTable1_16190_16220_7 (10329)
    // myTable1_16221_16250_8 (10468)
    // myTable1_16251_16281_9 (10499)
    // myTable1_16282_16312_10 (10196)
    // myTable1_16313_16342_11 (9136)
    // myTable1_16343_16373_0 (9292)
    // myTable1_16374_16404_1 (8736)
    // myTable1_16405_16435_2 (9378)

    // Expected merge tasks and result segments:
    // 1.
    //    {myTable1_16071_16101_3}
    //      -> {merged_100days_T1_0_myTable1_16071_16099_0, merged_100days_T1_0_myTable1_16100_16101_1}
    // 2.
    //    {merged_100days_T1_0_myTable1_16100_16101_1, myTable1_16102_16129_4, myTable1_16130_16159_5}
    //      -> {merged_100days_T2_0_myTable1_16100_???_0(15000), merged_100days_T2_0_myTable1_???_16159_1}
    //    {myTable1_16160_16189_6, myTable1_16190_16220_7}
    //      -> {merged_100days_T2_1_myTable1_16160_16199_0, merged_100days_T2_1_myTable1_16200_16220_1}
    // 3.
    //    {merged_100days_T2_1_myTable1_16200_16220_1, myTable1_16221_16250_8}
    //      -> {merged_100days_T3_0_myTable1_16200_???_0(15000), merged_100days_T3_0_myTable1_???_16250_1}
    //    {myTable1_16251_16281_9, myTable1_16282_16312_10}
    //      -> {merged_100days_T3_1_myTable1_16251_???_0(15000), merged_100days_T3_1_myTable1_???_16299_1,
    //      merged_100days_T3_1_myTable1_16300_16312_2}
    // 4.
    //    {merged_100days_T3_1_myTable1_16300_16312_2, myTable1_16313_16342_11, myTable1_16343_16373_0}
    //      -> {merged_100days_T4_0_myTable1_16300_???_0(15000), merged_100days_T4_0_myTable1_???_16373_1}
    //    {myTable1_16374_16404_1}
    //      -> {merged_100days_T4_1_16374_16399_0, merged_100days_T4_1_16400_16404_1}
    // 5.
    //    {merged_100days_T4_1_16400_16404_1, myTable1_16405_16435_2}
    //      -> {merged_100days_T5_0_myTable1_16400_16435_0}

    String sqlQuery = "SELECT count(*) FROM myTable1"; // 115545 rows for the test table
    JsonNode expectedJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    int[] expectedNumSubTasks = {1, 2, 2, 2, 1};
    int[] expectedNumSegmentsQueried = {13, 12, 13, 13, 12};
    long expectedWatermark = 16000 * 86_400_000L;
    String offlineTableName = TableNameBuilder.OFFLINE.tableNameWithType(SINGLE_LEVEL_CONCAT_TEST_TABLE);
    int numTasks = 0;
    for (String tasks = _taskManager.scheduleTasks(offlineTableName).get(MinionConstants.MergeRollupTask.TASK_TYPE);
        tasks != null; tasks =
        _taskManager.scheduleTasks(offlineTableName).get(MinionConstants.MergeRollupTask.TASK_TYPE), numTasks++) {
      assertEquals(_helixTaskResourceManager.getTaskConfigs(tasks).size(), expectedNumSubTasks[numTasks]);
      assertTrue(_helixTaskResourceManager.getTaskQueues()
          .contains(PinotHelixTaskResourceManager.getHelixJobQueueName(MinionConstants.MergeRollupTask.TASK_TYPE)));
      // Will not schedule task if there's incomplete task
      assertNull(
          _taskManager.scheduleTasks(offlineTableName).get(MinionConstants.RealtimeToOfflineSegmentsTask.TASK_TYPE));
      waitForTaskToComplete();

      // Check watermark
      MergeRollupTaskMetadata minionTaskMetadata = MergeRollupTaskMetadata
          .fromZNRecord(_taskManager.getClusterInfoAccessor().getMinionMergeRollupTaskZNRecord(offlineTableName));
      assertNotNull(minionTaskMetadata);
      assertEquals((long) minionTaskMetadata.getWatermarkMap().get("100days"), expectedWatermark);
      expectedWatermark += 100 * 86_400_000L;

      // Check metadata of merged segments
      for (SegmentZKMetadata metadata : _pinotHelixResourceManager.getSegmentsZKMetadata(offlineTableName)) {
        if (metadata.getSegmentName().startsWith("merged")) {
          // Check merged segment zk metadata
          assertNotNull(metadata.getCustomMap());
          assertEquals("100days",
              metadata.getCustomMap().get(MinionConstants.MergeRollupTask.SEGMENT_ZK_METADATA_MERGE_LEVEL_KEY));
          // Check merged segments are time partitioned
          assertEquals(metadata.getEndTimeMs() / (86_400_000L * 100), metadata.getStartTimeMs() / (86_400_000L * 100));
        }
      }

      // Check num total doc of merged segments are the same as the original segments
      JsonNode actualJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
      SqlResultComparator.areEqual(actualJson, expectedJson, sqlQuery);
      // Check query routing
      int numSegmentsQueried = actualJson.get("numSegmentsQueried").asInt();
      assertEquals(numSegmentsQueried, expectedNumSegmentsQueried[numTasks]);
    }
    // Check total tasks
    assertEquals(numTasks, 5);

    assertTrue(_controllerStarter.getControllerMetrics()
        .containsGauge("mergeRollupTaskDelayInNumBuckets.myTable1_OFFLINE.100days"));

    // Drop the table
    dropOfflineTable(SINGLE_LEVEL_CONCAT_TEST_TABLE);

    // Check if the task metadata is cleaned up on table deletion
    verifyTableDelete(offlineTableName);
  }

}