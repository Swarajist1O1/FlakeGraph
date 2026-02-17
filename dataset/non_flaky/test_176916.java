class DummyClass_176916 {
  @Test
  public void testSilhouetteCoefficientForClustering() {
    List<ClusterInfo> clusters = getClusters();
    SilhouetteCoefficient silhouetteCoefficient = new SilhouetteCoefficient(clusters);
    double eval = silhouetteCoefficient.evaluate(getRddOfVectors());
    log.info("Silhouette Coefficient for {} clusters: {}", clusters.size(), eval);
    assertEquals(0.48484126984126985, eval);
  }

}