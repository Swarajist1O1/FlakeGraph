class DummyClass_113857 {
@TestConfiguration
  public ResourceProvider workflowResourcesProvider() {
    return new TestResourcesProvider(Paths.get("dummy").toString());
  }

}