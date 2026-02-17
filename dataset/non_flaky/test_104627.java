class DummyClass_104627 {
  @Test
  public void testSingleLevelRollup()
      throws Exception {
    // The original segments are time partitioned by month:
    // segmentName (totalDocs)
    // myTable2_16071_16101_3_1, myTable2_16071_16101_3_2 (9746)
    // myTable2_16102_16129_4_1, myTable2_16102_16129_4_2 (8690)
    // myTable2_16130_16159_5_1, myTable2_16130_16159_5_2 (9621)
    // myTable2_16160_16189_6_1, myTable2_16160_16189_6_2 (9454)
    // myTable2_16190_16220_7_1, myTable2_16190_16220_7_2 (10329)
    // myTable2_16221_16250_8_1, myTable2_16221_16250_8_2 (10468)
    // myTable2_16251_16281_9_1, myTable2_16251_16281_9_2 (10499)
    // myTable2_16282_16312_10_1, myTable2_16282_16312_10_2 (10196)
    // myTable2_16313_16342_11_1, myTable2_16313_16342_11_2 (9136)
    // myTable2_16343_16373_0_1, myTable2_16343_16373_0_2 (9292)
    // myTable2_16374_16404_1_1, myTable2_16374_16404_1_2 (8736)
    // myTable2_16405_16435_2_1, myTable2_16405_16435_2_2 (9378)

    // Expected merge tasks and result segments:
    // 1.
    //    {myTable2_16071_16101_3_1, myTable2_16071_16101_3_2, myTable2_16102_16129_4_1, myTable2_16102_16129_4_2,
    //     myTable2_16130_16159_5_1, myTable2_16130_16159_5_2, myTable2_16160_16189_6_1, myTable2_16160_16189_6_2
    //     myTable2_16190_16220_7}
    //      -> {merged_150days_T1_0_myTable2_16065_16198_0, merged_150days_T1_0_myTable2_16205_16219_1}
    // 2.
    //    {merged_150days_T1_0_myTable2_16205_16219_1, myTable2_16221_16250_8_1, myTable2_16221_16250_8_2,
    //     myTable2_16251_16281_9_1, myTable2_16251_16281_9_2, myTable2_16282_16312_10_1
    //     myTable2_16282_16312_10_2, myTable2_16313_16342_11_1, myTable2_16313_16342_11_2,
    //     myTable2_16343_16373_0_1, myTable2_16343_16373_0_2}
    //      -> {merged_150days_1628644088146_0_myTable2_16205_16345_0,
    //          merged_150days_1628644088146_0_myTable2_16352_16373_1}
    // 3.
    //    {merged_150days_1628644088146_0_myTable2_16352_16373_1, myTable2_16374_16404_1_1, myTable2_16374_16404_1_2
    //     myTable2_16405_16435_2_1, myTable2_16405_16435_2_2}
    //      -> {merged_150days_1628644105127_0_myTable2_16352_16429_0}

    String sqlQuery = "SELECT count(*) FROM myTable2"; // 115545 rows for the test table
    JsonNode expectedJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    int[] expectedNumSegmentsQueried = {16, 7, 3};
    long expectedWatermark = 16050 * 86_400_000L;
    String offlineTableName = TableNameBuilder.OFFLINE.tableNameWithType(SINGLE_LEVEL_ROLLUP_TEST_TABLE);
    int numTasks = 0;
    for (String tasks = _taskManager.scheduleTasks(offlineTableName).get(MinionConstants.MergeRollupTask.TASK_TYPE);
        tasks != null; tasks =
        _taskManager.scheduleTasks(offlineTableName).get(MinionConstants.MergeRollupTask.TASK_TYPE), numTasks++) {
      assertEquals(_helixTaskResourceManager.getTaskConfigs(tasks).size(), 1);
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
      assertEquals((long) minionTaskMetadata.getWatermarkMap().get("150days"), expectedWatermark);
      expectedWatermark += 150 * 86_400_000L;

      // Check metadata of merged segments
      for (SegmentZKMetadata metadata : _pinotHelixResourceManager.getSegmentsZKMetadata(offlineTableName)) {
        if (metadata.getSegmentName().startsWith("merged")) {
          // Check merged segment zk metadata
          assertNotNull(metadata.getCustomMap());
          assertEquals("150days",
              metadata.getCustomMap().get(MinionConstants.MergeRollupTask.SEGMENT_ZK_METADATA_MERGE_LEVEL_KEY));
          // Check merged segments are time partitioned
          assertEquals(metadata.getEndTimeMs() / (86_400_000L * 150), metadata.getStartTimeMs() / (86_400_000L * 150));
        }
      }

      // Check total doc of merged segments are less than the original segments
      JsonNode actualJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
      assertTrue(
          actualJson.get("resultTable").get("rows").get(0).get(0).asInt() < expectedJson.get("resultTable").get("rows")
              .get(0).get(0).asInt());
      // Check query routing
      int numSegmentsQueried = actualJson.get("numSegmentsQueried").asInt();
      assertEquals(numSegmentsQueried, expectedNumSegmentsQueried[numTasks]);
    }

    // Check total doc is half of the original after all merge tasks are finished
    JsonNode actualJson = postSqlQuery(sqlQuery, _brokerBaseApiUrl);
    assertEquals(actualJson.get("resultTable").get("rows").get(0).get(0).asInt(),
        expectedJson.get("resultTable").get("rows").get(0).get(0).asInt() / 2);
    // Check time column is rounded
    JsonNode responseJson =
        postSqlQuery("SELECT count(*), DaysSinceEpoch FROM myTable2 GROUP BY DaysSinceEpoch ORDER BY DaysSinceEpoch");
    for (int i = 0; i < responseJson.get("resultTable").get("rows").size(); i++) {
      int daysSinceEpoch = responseJson.get("resultTable").get("rows").get(i).get(1).asInt();
      assertTrue(daysSinceEpoch % 7 == 0);
    }
    // Check total tasks
    assertEquals(numTasks, 3);

    assertTrue(_controllerStarter.getControllerMetrics()
        .containsGauge("mergeRollupTaskDelayInNumBuckets.myTable2_OFFLINE.150days"));
  }

}