class DummyClass_176852 {
  @Test
  public void testCopyEquals() {
    DoubleWeightedMean mean = new DoubleWeightedMean();
    mean.increment(0.2, 4.0);
    mean.increment(-0.1, 2.0);
    DoubleWeightedMean copy = mean.copy();
    assertEquals(copy, mean);
    assertEquals(copy.hashCode(), mean.hashCode());
    DoubleWeightedMean zero = new DoubleWeightedMean();
    mean.clear();
    assertEquals(zero, mean);
  }

}