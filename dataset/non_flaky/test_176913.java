class DummyClass_176913 {
  @Test
  public void testFetchSampleEvalData() {
    JavaRDD<Vector> evalData = SilhouetteCoefficient.fetchSampleData(getRddOfVectors());
    assertEquals(6, evalData.count());
  }

}