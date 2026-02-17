class DummyClass_176834 {
  @Test
  public void testSolveFToD() {
    RealMatrix a = new Array2DRowRealMatrix(new double[][] {
        {1.3, -2.0, 3.0},
        {2.0, 0.0, 5.0},
        {0.0, -1.5, 5.5},
    });
    Solver solver = new LinearSystemSolver().getSolver(a);
    assertNotNull(solver);
    double[] y = solver.solveFToD(new float[] {1.0f, 2.0f, 6.5f});
    assertArrayEquals(
        new double[] {-1.9560439560439564,0.002197802197802894,1.1824175824175824}, y);
  }

}