class DummyClass_345 {
  @Test
  public void testContains() throws Exception {
    DatanodeDescriptor nodeNotInMap = 
      DFSTestUtil.getDatanodeDescriptor("8.8.8.8", "/d2/r4");
    for (int i=0; i < dataNodes.length; i++) {
      assertTrue(cluster.contains(dataNodes[i]));
    }
    assertFalse(cluster.contains(nodeNotInMap));
  }

}