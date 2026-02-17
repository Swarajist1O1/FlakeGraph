class DummyClass_176927 {
  @Test
  public void testLoad() {
    RescorerProvider provider = AbstractRescorerProvider.loadRescorerProviders(
        "com.cloudera.oryx.app.als.NullProvider2");
    assertTrue(provider instanceof NullProvider2);
    RescorerProvider multiProvider = AbstractRescorerProvider.loadRescorerProviders(
        "com.cloudera.oryx.app.als.NullProvider1,com.cloudera.oryx.app.als.NullProvider2");
    assertTrue(multiProvider instanceof MultiRescorerProvider);
  }

}