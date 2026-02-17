class DummyClass_57229 {
  @Test
  public void testGlobalStatsCRUDOperations() throws Exception {
    Connection connection = getConnection();

    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getTables(null, null,
        GLOBAL_STATS_TABLE_NAME, null);

    while (resultSet.next()) {
      Assert.assertEquals(GLOBAL_STATS_TABLE_NAME,
          resultSet.getString("TABLE_NAME"));
    }

    GlobalStatsDao dao = getDao(GlobalStatsDao.class);

    long now = System.currentTimeMillis();
    GlobalStats newRecord = new GlobalStats();
    newRecord.setLastUpdatedTimestamp(new Timestamp(now));
    newRecord.setKey("key1");
    newRecord.setValue(500L);

    // Create
    dao.insert(newRecord);
    GlobalStats newRecord2 = new GlobalStats();
    newRecord2.setLastUpdatedTimestamp(new Timestamp(now + 1000L));
    newRecord2.setKey("key2");
    newRecord2.setValue(10L);
    dao.insert(newRecord2);

    // Read
    GlobalStats dbRecord = dao.findById("key1");

    Assert.assertEquals("key1", dbRecord.getKey());
    Assert.assertEquals(Long.valueOf(500), dbRecord.getValue());
    Assert.assertEquals(new Timestamp(now), dbRecord.getLastUpdatedTimestamp());

    dbRecord = dao.findById("key2");
    Assert.assertEquals("key2", dbRecord.getKey());
    Assert.assertEquals(Long.valueOf(10), dbRecord.getValue());
    Assert.assertEquals(new Timestamp(now + 1000L),
        dbRecord.getLastUpdatedTimestamp());

    // Update
    dbRecord.setValue(100L);
    dbRecord.setLastUpdatedTimestamp(new Timestamp(now + 2000L));
    dao.update(dbRecord);

    // Read updated
    dbRecord = dao.findById("key2");

    Assert.assertEquals(new Timestamp(now + 2000L),
        dbRecord.getLastUpdatedTimestamp());
    Assert.assertEquals(Long.valueOf(100L), dbRecord.getValue());

    // Delete
    dao.deleteById("key1");

    // Verify
    dbRecord = dao.findById("key1");

    Assert.assertNull(dbRecord);
  }

}