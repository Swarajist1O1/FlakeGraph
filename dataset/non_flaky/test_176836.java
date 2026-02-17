class DummyClass_176836 {
  @Test
  public void testIsNonSingular() {
    RealMatrix nonSingular = new Array2DRowRealMatrix(new double[][] {
        {1.3, -2.0, 3.0},
        {2.0, 0.0, 5.0},
        {0.0, -1.5, 5.5},
    });
    assertTrue(new LinearSystemSolver().isNonSingular(nonSingular));
    RealMatrix singular = new Array2DRowRealMatrix(new double[][] {
        {1.3, -2.0, 3.0},
        {2.6, -4.0, 6.0},
        {0.0, -1.5, 5.5},
    });
    assertFalse(new LinearSystemSolver().isNonSingular(singular));
  }

}