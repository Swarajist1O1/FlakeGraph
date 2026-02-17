class DummyClass_35739 {
  @Test
  public void testDataFabricEndpoints() throws Exception {
    // endpoints from DatasetInstanceHandler
    assertContent("/v3/namespaces/default/data/datasets/myDataset", DEFAULT_AUDIT);
    // endpoints from DatasetTypeHandler
    assertContent("/v3/namespaces/default/data/modules/myModule",
                  new AuditLogConfig(HttpMethod.PUT, false, false, ImmutableList.of("X-Class-Name")));
  }

}