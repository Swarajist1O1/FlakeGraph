class DummyClass_176929 {
  @Test(expected = ClassCastException.class)
  public void testWrongClass() {
    AbstractRescorerProvider.loadRescorerProviders(
        "com.cloudera.oryx.app.als.AbstractRescorerProviderTest");
  }

}