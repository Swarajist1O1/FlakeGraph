class DummyClass_176845 {
  @Test
  public void testNorm() {
    assertEquals(0.0, VectorMath.norm(new float[] {0.0f}), FLOAT_EPSILON);
    assertEquals(3.674234614174767, VectorMath.norm(VEC1), FLOAT_EPSILON);
    assertEquals(10.72800074571213, VectorMath.norm(VEC2), FLOAT_EPSILON);
  }

}