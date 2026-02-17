class DummyClass_176915 {
  @Test
  public void testDaviesBouldinIndexForClustering() {
    List<ClusterInfo> clusters = getClusters();
    DaviesBouldinIndex daviesBouldinIndex = new DaviesBouldinIndex(clusters);
    double eval = daviesBouldinIndex.evaluate(getRddOfVectors());
    log.info("Davies Bouldin Index for {} clusters: {}", clusters.size(), eval);
    assertEquals(0.638888888888889, eval);
  }

}