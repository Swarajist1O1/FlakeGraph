class DummyClass_176844 {
  @Test
  public void testBig() {
    float[] a = { 1.0e20f };
    assertEquals((double) 1.0e20f * (double) 1.0e20f, VectorMath.dot(a, a));
  }

}