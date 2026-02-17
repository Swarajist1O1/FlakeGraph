class DummyClass_57223 {
  @Test
  public void testFileCountBySizeCRUDOperations() throws SQLException {
    Connection connection = getConnection();

    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getTables(null, null,
        FILE_COUNT_BY_SIZE_TABLE_NAME, null);

    while (resultSet.next()) {
      Assert.assertEquals(FILE_COUNT_BY_SIZE_TABLE_NAME,
          resultSet.getString("TABLE_NAME"));
    }

    FileCountBySizeDao fileCountBySizeDao = getDao(FileCountBySizeDao.class);
    UtilizationSchemaDefinition utilizationSchemaDefinition =
        getSchemaDefinition(UtilizationSchemaDefinition.class);

    FileCountBySize newRecord = new FileCountBySize();
    newRecord.setVolume("vol1");
    newRecord.setBucket("bucket1");
    newRecord.setFileSize(1024L);
    newRecord.setCount(1L);

    fileCountBySizeDao.insert(newRecord);

    Record3<String, String, Long> recordToFind = utilizationSchemaDefinition
        .getDSLContext().newRecord(FILE_COUNT_BY_SIZE.VOLUME,
            FILE_COUNT_BY_SIZE.BUCKET,
            FILE_COUNT_BY_SIZE.FILE_SIZE)
        .value1("vol1")
        .value2("bucket1")
        .value3(1024L);
    FileCountBySize dbRecord = fileCountBySizeDao.findById(recordToFind);
    assertEquals(Long.valueOf(1), dbRecord.getCount());

    dbRecord.setCount(2L);
    fileCountBySizeDao.update(dbRecord);

    dbRecord = fileCountBySizeDao.findById(recordToFind);
    assertEquals(Long.valueOf(2), dbRecord.getCount());

    Table<FileCountBySizeRecord> fileCountBySizeRecordTable =
        fileCountBySizeDao.getTable();
    List<UniqueKey<FileCountBySizeRecord>> tableKeys =
        fileCountBySizeRecordTable.getKeys();
    for (UniqueKey key : tableKeys) {
      String name = key.getName();
    }
  }

}