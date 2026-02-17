class DummyClass_104691 {
  @Test
  public void testConvertToRawIndexTask()
      throws Exception {
    String offlineTableName = TableNameBuilder.OFFLINE.tableNameWithType(getTableName());

    File testDataDir = new File(CommonConstants.Server.DEFAULT_INSTANCE_DATA_DIR + "-0", offlineTableName);
    if (!testDataDir.isDirectory()) {
      testDataDir = new File(CommonConstants.Server.DEFAULT_INSTANCE_DATA_DIR + "-1", offlineTableName);
    }
    Assert.assertTrue(testDataDir.isDirectory());
    File tableDataDir = testDataDir;

    // Check that all columns have dictionary
    File[] indexDirs = tableDataDir.listFiles();
    Assert.assertNotNull(indexDirs);
    for (File indexDir : indexDirs) {
      SegmentMetadata segmentMetadata = new SegmentMetadataImpl(indexDir);
      for (String columnName : segmentMetadata.getSchema().getColumnNames()) {
        Assert.assertTrue(segmentMetadata.getColumnMetadataFor(columnName).hasDictionary());
      }
    }

    // Should create the task queues and generate a ConvertToRawIndexTask task with 5 child tasks
    Assert.assertNotNull(_taskManager.scheduleTasks().get(ConvertToRawIndexTask.TASK_TYPE));
    Assert.assertTrue(_helixTaskResourceManager.getTaskQueues()
        .contains(PinotHelixTaskResourceManager.getHelixJobQueueName(ConvertToRawIndexTask.TASK_TYPE)));

    // Should generate one more ConvertToRawIndexTask task with 3 child tasks
    Assert.assertNotNull(_taskManager.scheduleTasks().get(ConvertToRawIndexTask.TASK_TYPE));

    // Should not generate more tasks
    Assert.assertNull(_taskManager.scheduleTasks().get(ConvertToRawIndexTask.TASK_TYPE));

    // Wait at most 600 seconds for all tasks COMPLETED and new segments refreshed
    TestUtils.waitForCondition(input -> {
      // Check task state
      for (TaskState taskState : _helixTaskResourceManager.getTaskStates(ConvertToRawIndexTask.TASK_TYPE).values()) {
        if (taskState != TaskState.COMPLETED) {
          return false;
        }
      }

      // Check segment ZK metadata
      for (SegmentZKMetadata segmentZKMetadata : _helixResourceManager.getSegmentsZKMetadata(offlineTableName)) {
        Map<String, String> customMap = segmentZKMetadata.getCustomMap();
        if (customMap == null || customMap.size() != 1 || !customMap
            .containsKey(ConvertToRawIndexTask.TASK_TYPE + MinionConstants.TASK_TIME_SUFFIX)) {
          return false;
        }
      }

      // Check segment metadata
      File[] indexDirs1 = tableDataDir.listFiles();
      Assert.assertNotNull(indexDirs1);
      for (File indexDir : indexDirs1) {
        SegmentMetadata segmentMetadata;

        // Segment metadata file might not exist if the segment is refreshing
        try {
          segmentMetadata = new SegmentMetadataImpl(indexDir);
        } catch (Exception e) {
          return false;
        }

        // The columns in COLUMNS_TO_CONVERT should have raw index
        List<String> rawIndexColumns = Arrays.asList(StringUtils.split(COLUMNS_TO_CONVERT, ','));
        for (String columnName : segmentMetadata.getSchema().getColumnNames()) {
          if (rawIndexColumns.contains(columnName)) {
            if (segmentMetadata.getColumnMetadataFor(columnName).hasDictionary()) {
              return false;
            }
          } else {
            if (!segmentMetadata.getColumnMetadataFor(columnName).hasDictionary()) {
              return false;
            }
          }
        }
      }

      return true;
    }, 600_000L, "Failed to get all tasks COMPLETED and new segments refreshed");
  }

}