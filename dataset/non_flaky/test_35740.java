class DummyClass_35740 {
  @Test
  public void testAppFabricEndpoints() throws Exception {
    // endpoints from AppLifecycleHttpHandler
    assertContent("/v3/namespaces/default/apps/myApp", DEFAULT_AUDIT);
    assertContent("/v3/namespaces/default/apps",
                  new AuditLogConfig(HttpMethod.POST, false, true,
                                     ImmutableList.of(AbstractAppFabricHttpHandler.ARCHIVE_NAME_HEADER,
                                                       AbstractAppFabricHttpHandler.APP_CONFIG_HEADER,
                                                       AbstractAppFabricHttpHandler.PRINCIPAL_HEADER,
                                                       AbstractAppFabricHttpHandler.SCHEDULES_HEADER)));
    // endpoints from ArtifactHttpHandler
    assertContent("/v3/namespaces/default/artifacts/myArtifact/versions/1.0/properties", DEFAULT_AUDIT);
    assertContent("/v3/namespaces/default/artifacts/myArtifact",
                  new AuditLogConfig(HttpMethod.POST, false, false,
                                     ImmutableList.of("Artifact-Version", "Artifact-Extends", "Artifact-Plugins")));
    // endpoints from AuthorizationHandler
    assertContent("/v3/security/authorization/privileges/grant",
                  new AuditLogConfig(HttpMethod.POST, true, false, EMPTY_HEADERS));
    // endpoints from ConsoleSettingsHttpHandler
    assertContent("/v3/configuration/user/", DEFAULT_AUDIT);
    // endpoints from MetadataHttpHandler
    assertContent("/v3/namespaces/default/apps/app1/metadata/properties",
                  new AuditLogConfig(HttpMethod.POST, true, false, EMPTY_HEADERS));
    // endpoints from MonitorHttpHandler
    assertContent("/v3/system/services/appfabric/instances", DEFAULT_AUDIT);
    // endpoints from NamespaceHttpHandler
    assertContent("/v3/namespaces/default", DEFAULT_AUDIT);
    // endpoints from PreferencesHttpHandler
    assertContent("/v3/preferences", DEFAULT_AUDIT);
    // endpoints from ProgramLifecycleHttpHandler
    assertContent("/v3/namespaces/default/stop", new AuditLogConfig(HttpMethod.POST, true, true, EMPTY_HEADERS));
    // endpoints from SecureStoreHandler
    assertContent("/v3/namespaces/default/securekeys/myKey", DEFAULT_AUDIT);
    // endpoints from TransactionHttpHandler
    assertContent("/v3/transactions/invalid/remove/until",
                  new AuditLogConfig(HttpMethod.POST, true, false, EMPTY_HEADERS));
  }

}