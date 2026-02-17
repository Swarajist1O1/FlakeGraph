class DummyClass_104692 {
  @Test
  public void testPinotHelixResourceManagerAPIs() {
    // Instance APIs
    Assert.assertEquals(_helixResourceManager.getAllInstances().size(), 5);
    Assert.assertEquals(_helixResourceManager.getOnlineInstanceList().size(), 5);
    Assert.assertEquals(_helixResourceManager.getOnlineUnTaggedBrokerInstanceList().size(), 0);
    Assert.assertEquals(_helixResourceManager.getOnlineUnTaggedServerInstanceList().size(), 0);

    // Table APIs
    String rawTableName = getTableName();
    String offlineTableName = TableNameBuilder.OFFLINE.tableNameWithType(rawTableName);
    String realtimeTableName = TableNameBuilder.REALTIME.tableNameWithType(rawTableName);
    List<String> tableNames = _helixResourceManager.getAllTables();
    Assert.assertEquals(tableNames.size(), 2);
    Assert.assertTrue(tableNames.contains(offlineTableName));
    Assert.assertTrue(tableNames.contains(realtimeTableName));
    Assert.assertEquals(_helixResourceManager.getAllRawTables(), Collections.singletonList(rawTableName));
    Assert.assertEquals(_helixResourceManager.getAllRealtimeTables(), Collections.singletonList(realtimeTableName));

    // Tenant APIs
    Assert.assertEquals(_helixResourceManager.getAllBrokerTenantNames(), Collections.singleton("TestTenant"));
    Assert.assertEquals(_helixResourceManager.getAllServerTenantNames(), Collections.singleton("TestTenant"));
  }

}