class DummyClass_176837 {
  @Test
  public void testApparentRank() {
    RealMatrix nearSingular = new Array2DRowRealMatrix(new double[][] {
        {1.31, -2.0, 3.0},
        {2.6, -4.01, 6.01},
        {0.0, -1.5, 5.5},
    });
    try {
      new LinearSystemSolver().getSolver(nearSingular);
    } catch (SingularMatrixSolverException smse) {
      assertEquals(2, smse.getApparentRank());
    }
  }

}