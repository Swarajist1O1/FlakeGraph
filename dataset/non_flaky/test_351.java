class DummyClass_351 {
  @Test
  public void testRemove() throws Exception {
    for(int i=0; i<dataNodes.length; i++) {
      cluster.remove(dataNodes[i]);
    }
    for(int i=0; i<dataNodes.length; i++) {
      assertFalse(cluster.contains(dataNodes[i]));
    }
    assertEquals(0, cluster.getNumOfLeaves());
    assertEquals(0, cluster.clusterMap.getChildren().size());
    for(int i=0; i<dataNodes.length; i++) {
      cluster.add(dataNodes[i]);
    }
  }

}