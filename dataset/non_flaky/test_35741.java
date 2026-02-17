class DummyClass_35741 {
  @Test
  public void testExploreEndpoints() throws Exception {
    // endpoints from ExploreExecutorHttpHandler
    assertContent("/v3/namespaces/default/data/explore/datasets/myDataset/update",
                  new AuditLogConfig(HttpMethod.POST, true, false, EMPTY_HEADERS));
    // endpoints from NamespacedExploreMetadataHttpHandler
    assertContent("/v3/namespaces/default/data/explore/jdbc/tables",
                  new AuditLogConfig(HttpMethod.POST, true, false, EMPTY_HEADERS));
    // endpoints from NamespacedExploreQueryExecutorHttpHandler
    assertContent("/v3/namespaces/default/data/explore/queries",
                  new AuditLogConfig(HttpMethod.POST, true, false, EMPTY_HEADERS));
  }

}