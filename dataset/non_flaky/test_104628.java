class DummyClass_104628 {
  @Test
  public void testMultiLevelConcat()
      throws Exception {
    // The original segments are time partitioned by month:
    // segmentName (totalDocs)
    // myTable3_16071_16101_3 (9746)
    // myTable3_16102_16129_4 (8690)
    // myTable3_16130_16159_5 (9621)
    // myTable3_16160_16189_6 (9454)
    // myTable3_16190_16220_7 (10329)
    // myTable3_16221_16250_8 (10468)
    // myTable3_16251_16281_9 (10499)
    // myTable3_16282_16312_10 (10196)
    // myTable3_16313_16342_11 (9136)
    // myTable3_16343_16373_0 (9292)
    // myTable3_16374_16404_1 (8736)
    // myTable3_16405_16435_2 (9378)

    // Expected merge tasks and results:
    // 1.
    //    45days: {myTable3_16071_16101_3, myTable3_16102_16129_4}
    //      -> {merged_45days_T1_0_myTable3_16071_16109_0, merged_45days_T1_0_myTable3_16110_16129_1}
    //    watermark: {45days: 16065, 90days: null}
    // 2.
    //    45days: {merged_45days_T1_0_myTable3_16110_16129_1, myTable3_16130_16159_5}
    //      -> {merged_45days_T2_0_myTable3_16110_16154_0, merged_45days_T2_0_myTable3_16155_16159_1}
    //    90days: {merged_45days_T1_0_myTable3_16071_16109_0}
    //      -> {merged_90days_T2_0_myTable3_16071_16109_0}
    //    watermark: {45days: 16110, 90days: 16020}
    // 3.
    //    45days: {merged_45days_T2_0_myTable3_16155_16159_1, myTable3_16160_16189_6, myTable3_16190_16220_7}
    //      -> {merged_45days_T3_0_myTable3_16155_16199_0, merged_45days_T3_0_myTable3_16200_16220_1}
    //    watermark: {45days: 16155, 90days: 16020}
    // 4.
    //    45days: {merged_45days_T3_-_myTable3_16200_16220_1, myTable3_16221_16250_8}
    //      -> {merged_45days_T4_0_myTable3_16200_16244_0, merged_45days_T4_0_myTable3_16245_16250_1}
    //    90days: {merged_45days_T2_0_myTable3_16110_16154_0, merged_45days_T3_0_myTable3_16155_16199_0}
    //      -> {merged_90days_T4_0_myTable3_16110_16199_0}
    //    watermark: {45days: 16200, 90days: 16110}
    // 5.
    //    45days: {merged_45days_T4_0_myTable3_16245_16250_1, myTable3_16251_16281_9, myTable3_16282_16312_10}
    //      -> {merged_45days_T5_0_myTable3_16245_16289_0, merged_45days_T5_0_myTable3_16290_16312_1}
    //    watermark: {45days: 16245, 90days: 16110}
    // 6.
    //    45days: {merged_45days_T5_0_myTable3_16290_16312_1, myTable3_16313_16342_11}
    //      -> {merged_45days_T6_0_myTable3_16290_16334_0, merged_45days_T6_0_myTable3_16335_16342_1}
    //    90days: {merged_45days_T4_0_myTable3_16200_16244_0, merged_45days_T5_0_myTable3_16245_16289_0}
    //      -> {merged_90days_T6_0_myTable3_16200_16289_0}
    //    watermark: {45days: 16290, 90days: 16200}
    // 7.
    //    45days: {merged_45days_T6_0_myTable3_16335_16342_1, myTable_16343_16373_0, myTable_16374_16404_1}
    //      -> {merged_45days_T7_0_myTable3_16335_16379_0, merged_45days_T7_0_myTable3_16380_16404_1}
    //    watermark: {45days: 16335, 90days: 16200}
    // 8.
    //    45days: {merged_45days_T7_0_myTable3_16380_16404_1, myTable3_16405_16435_2}
    //      -> {merged_45days_T8_0_myTable3_16380_16424_0, merged_45days_T8_1_myTable3_16425_16435_1}
    //    90days: {merged_45days_T6_0_myTable3_16290_16334_0, merged_45days_T7_0_myTable3_16335_16379_0}
    //      -> {merged_90days_T8_0_myTable3_16290_16379_0}
    //    watermark: {45days:16380, 90days: 16290}
    // 9.
    //    45days: no segment left, not scheduling
    //    90days: [16380, 16470) is not a valid merge window because windowEndTime > 45days watermark, not scheduling

    String sqlQuery = "SELECT count(*) FROM myTable3"; // 115545 rows for the test table
    JsonNode expectedJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    int[] expectedNumSubTasks = {1, 2, 1, 2, 1, 2, 1, 2, 1};
    int[] expectedNumSegmentsQueried = {12, 12, 11, 10, 9, 8, 7, 6, 5};
    Long[] expectedWatermarks45Days = {16065L, 16110L, 16155L, 16200L, 16245L, 16290L, 16335L, 16380L};
    Long[] expectedWatermarks90Days = {null, 16020L, 16020L, 16110L, 16110L, 16200L, 16200L, 16290L};
    for (int i = 0; i < expectedWatermarks45Days.length; i++) {
      expectedWatermarks45Days[i] *= 86_400_000L;
    }
    for (int i = 1; i < expectedWatermarks90Days.length; i++) {
      expectedWatermarks90Days[i] *= 86_400_000L;
    }

    String offlineTableName = TableNameBuilder.OFFLINE.tableNameWithType(MULTI_LEVEL_CONCAT_TEST_TABLE);
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
      assertEquals(minionTaskMetadata.getWatermarkMap().get("45days"), expectedWatermarks45Days[numTasks]);
      assertEquals(minionTaskMetadata.getWatermarkMap().get("90days"), expectedWatermarks90Days[numTasks]);

      // Check metadata of merged segments
      for (SegmentZKMetadata metadata : _pinotHelixResourceManager.getSegmentsZKMetadata(offlineTableName)) {
        if (metadata.getSegmentName().startsWith("merged")) {
          // Check merged segment zk metadata
          assertNotNull(metadata.getCustomMap());
          if (metadata.getSegmentName().startsWith("merged_45days")) {
            assertEquals("45days",
                metadata.getCustomMap().get(MinionConstants.MergeRollupTask.SEGMENT_ZK_METADATA_MERGE_LEVEL_KEY));
            assertEquals(metadata.getEndTimeMs() / (86_400_000L * 45), metadata.getStartTimeMs() / (86_400_000L * 45));
          }
          if (metadata.getSegmentName().startsWith("merged_90days")) {
            assertEquals("90days",
                metadata.getCustomMap().get(MinionConstants.MergeRollupTask.SEGMENT_ZK_METADATA_MERGE_LEVEL_KEY));
            assertEquals(metadata.getEndTimeMs() / (86_400_000L * 90), metadata.getStartTimeMs() / (86_400_000L * 90));
          }
        }
      }

      // Check total doc of merged segments are the same as the original segments
      JsonNode actualJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
      SqlResultComparator.areEqual(actualJson, expectedJson, sqlQuery);
      // Check query routing
      int numSegmentsQueried = actualJson.get("numSegmentsQueried").asInt();
      assertEquals(numSegmentsQueried, expectedNumSegmentsQueried[numTasks]);
    }
    // Check total tasks
    assertEquals(numTasks, 8);

    assertTrue(_controllerStarter.getControllerMetrics()
        .containsGauge("mergeRollupTaskDelayInNumBuckets.myTable3_OFFLINE.45days"));
    assertTrue(_controllerStarter.getControllerMetrics()
        .containsGauge("mergeRollupTaskDelayInNumBuckets.myTable3_OFFLINE.90days"));
  }

}