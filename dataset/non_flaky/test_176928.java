class DummyClass_176928 {
  @Test(expected = IllegalArgumentException.class)
  public void testNoClass() {
    AbstractRescorerProvider.loadRescorerProviders("noSuchClass");
  }

}