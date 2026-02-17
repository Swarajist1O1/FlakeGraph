class DummyClass_176841 {
  @Test
  public void testToDoubles() {
    assertArrayEquals(new double[] {1.2}, VectorMath.toDoubles(1.2f), FLOAT_EPSILON);
  }

}