class DummyClass_176914 {
  @Test
  public void testDunnIndexForClustering() {
    List<ClusterInfo> clusters = getClusters();
    DunnIndex dunnIndex = new DunnIndex(clusters);
    double eval = dunnIndex.evaluate(getRddOfVectors());
    log.info("Dunn Index for {} clusters: {}", clusters.size(), eval);
    assertEquals(1.7142857142857142, eval);
  }

}