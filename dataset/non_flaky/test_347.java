class DummyClass_347 {
  @Test
  public void testCreateInvalidTopology() throws Exception {
    NetworkTopology invalCluster =
        NetworkTopology.getInstance(new Configuration());
    DatanodeDescriptor invalDataNodes[] = new DatanodeDescriptor[] {
        DFSTestUtil.getDatanodeDescriptor("1.1.1.1", "/d1/r1"),
        DFSTestUtil.getDatanodeDescriptor("2.2.2.2", "/d1/r1"),
        DFSTestUtil.getDatanodeDescriptor("3.3.3.3", "/d1")
    };
    invalCluster.add(invalDataNodes[0]);
    invalCluster.add(invalDataNodes[1]);
    try {
      invalCluster.add(invalDataNodes[2]);
      fail("expected InvalidTopologyException");
    } catch (NetworkTopology.InvalidTopologyException e) {
      assertTrue(e.getMessage().startsWith("Failed to add "));
      assertTrue(e.getMessage().contains(
          "You cannot have a rack and a non-rack node at the same " +
          "level of the network topology."));
    }
  }

}