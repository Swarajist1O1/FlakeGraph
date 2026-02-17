class DummyClass_57222 {
  @Test
  public void testClusterGrowthDailyCRUDOperations() throws Exception {
    // Verify table exists
    Connection connection = getConnection();

    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getTables(null, null,
        CLUSTER_GROWTH_DAILY_TABLE_NAME, null);

    while (resultSet.next()) {
      Assert.assertEquals(CLUSTER_GROWTH_DAILY_TABLE_NAME,
          resultSet.getString("TABLE_NAME"));
    }

    ClusterGrowthDailyDao dao = getDao(ClusterGrowthDailyDao.class);
    long now = System.currentTimeMillis();
    ClusterGrowthDaily newRecord = new ClusterGrowthDaily();
    newRecord.setTimestamp(new Timestamp(now));
    newRecord.setDatanodeId(10);
    newRecord.setDatanodeHost("host1");
    newRecord.setRackId("rack1");
    newRecord.setAvailableSize(1024L);
    newRecord.setUsedSize(512L);
    newRecord.setContainerCount(10);
    newRecord.setBlockCount(25);

    // Create
    dao.insert(newRecord);

    // Read
    ClusterGrowthDaily dbRecord =
        dao.findById(getDslContext().newRecord(CLUSTER_GROWTH_DAILY.TIMESTAMP,
            CLUSTER_GROWTH_DAILY.DATANODE_ID)
            .value1(new Timestamp(now)).value2(10));

    Assert.assertEquals("host1", dbRecord.getDatanodeHost());
    Assert.assertEquals("rack1", dbRecord.getRackId());
    Assert.assertEquals(Long.valueOf(1024), dbRecord.getAvailableSize());
    Assert.assertEquals(Long.valueOf(512), dbRecord.getUsedSize());
    Assert.assertEquals(Integer.valueOf(10), dbRecord.getContainerCount());
    Assert.assertEquals(Integer.valueOf(25), dbRecord.getBlockCount());

    // Update
    dbRecord.setUsedSize(700L);
    dbRecord.setBlockCount(30);
    dao.update(dbRecord);

    // Read updated
    dbRecord =
        dao.findById(getDslContext().newRecord(CLUSTER_GROWTH_DAILY.TIMESTAMP,
            CLUSTER_GROWTH_DAILY.DATANODE_ID)
            .value1(new Timestamp(now)).value2(10));

    Assert.assertEquals(Long.valueOf(700), dbRecord.getUsedSize());
    Assert.assertEquals(Integer.valueOf(30), dbRecord.getBlockCount());

    // Delete
    dao.deleteById(getDslContext().newRecord(CLUSTER_GROWTH_DAILY.TIMESTAMP,
        CLUSTER_GROWTH_DAILY.DATANODE_ID)
        .value1(new Timestamp(now)).value2(10));

    // Verify
    dbRecord =
        dao.findById(getDslContext().newRecord(CLUSTER_GROWTH_DAILY.TIMESTAMP,
            CLUSTER_GROWTH_DAILY.DATANODE_ID)
            .value1(new Timestamp(now)).value2(10));

    Assert.assertNull(dbRecord);
  }

}