class DummyClass_57225 {
  @Test
  public void testReconTaskStatusCRUDOperations() throws Exception {
    // Verify table exists
    Connection connection = getConnection();
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getTables(null, null,
        RECON_TASK_STATUS_TABLE_NAME, null);

    while (resultSet.next()) {
      Assert.assertEquals(RECON_TASK_STATUS_TABLE_NAME,
          resultSet.getString("TABLE_NAME"));
    }

    ReconTaskStatusDao dao = getDao(ReconTaskStatusDao.class);
    long now = System.currentTimeMillis();
    ReconTaskStatus newRecord = new ReconTaskStatus();
    newRecord.setTaskName("HelloWorldTask");
    newRecord.setLastUpdatedTimestamp(now);
    newRecord.setLastUpdatedSeqNumber(100L);

    // Create
    dao.insert(newRecord);

    ReconTaskStatus newRecord2 = new ReconTaskStatus();
    newRecord2.setTaskName("GoodbyeWorldTask");
    newRecord2.setLastUpdatedTimestamp(now);
    newRecord2.setLastUpdatedSeqNumber(200L);
    // Create
    dao.insert(newRecord2);

    // Read
    ReconTaskStatus dbRecord = dao.findById("HelloWorldTask");

    Assert.assertEquals("HelloWorldTask", dbRecord.getTaskName());
    Assert.assertEquals(Long.valueOf(now), dbRecord.getLastUpdatedTimestamp());
    Assert.assertEquals(Long.valueOf(100), dbRecord.getLastUpdatedSeqNumber());

    // Update
    dbRecord.setLastUpdatedSeqNumber(150L);
    dao.update(dbRecord);

    // Read updated
    dbRecord = dao.findById("HelloWorldTask");
    Assert.assertEquals(Long.valueOf(150), dbRecord.getLastUpdatedSeqNumber());

    // Delete
    dao.deleteById("GoodbyeWorldTask");

    // Verify
    dbRecord = dao.findById("GoodbyeWorldTask");

    Assert.assertNull(dbRecord);
  }

}