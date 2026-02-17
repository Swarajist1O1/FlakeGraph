class DummyClass_176846 {
  @Test
  public void testTransposeTimesSelf() {
    Map<Integer,float[]> a = new HashMap<>();
    a.put(-1, new float[] {1.3f, -2.0f, 3.0f});
    a.put(1, new float[] {2.0f, 0.0f, 5.0f});
    a.put(3, new float[] {0.0f, -1.5f, 5.5f});
    RealMatrix ata = VectorMath.transposeTimesSelf(a.values());
    RealMatrix expected = new Array2DRowRealMatrix(new double[][] {
        {5.69, -2.6, 13.9},
        {-2.6, 6.25, -14.25},
        {13.9, -14.25, 64.25}
    });
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        assertEquals(expected.getEntry(row, col), ata.getEntry(row, col), FLOAT_EPSILON);
      }
    }
  }

}