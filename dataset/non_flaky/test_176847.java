class DummyClass_176847 {
  @Test
  public void testNone() {
    DoubleWeightedMean mean = new DoubleWeightedMean();
    assertEquals(0, mean.getN());
    assertTrue(Double.isNaN(mean.getResult()));
  }

}