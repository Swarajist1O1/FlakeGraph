class DummyClass_349 {
  @Test
  public void testGetDistance() throws Exception {
    assertEquals(cluster.getDistance(dataNodes[0], dataNodes[0]), 0);
    assertEquals(cluster.getDistance(dataNodes[0], dataNodes[1]), 2);
    assertEquals(cluster.getDistance(dataNodes[0], dataNodes[3]), 4);
    assertEquals(cluster.getDistance(dataNodes[0], dataNodes[6]), 6);
    // verify the distance is zero as long as two nodes have the same path.
    // They don't need to refer to the same object.
    NodeBase node1 = new NodeBase(dataNodes[0].getHostName(),
        dataNodes[0].getNetworkLocation());
    NodeBase node2 = new NodeBase(dataNodes[0].getHostName(),
        dataNodes[0].getNetworkLocation());
    assertEquals(0, cluster.getDistance(node1, node2));
    // verify the distance can be computed by path.
    // They don't need to refer to the same object or parents.
    NodeBase node3 = new NodeBase(dataNodes[3].getHostName(),
        dataNodes[3].getNetworkLocation());
    NodeBase node4 = new NodeBase(dataNodes[6].getHostName(),
        dataNodes[6].getNetworkLocation());
    assertEquals(0, NetworkTopology.getDistanceByPath(node1, node2));
    assertEquals(4, NetworkTopology.getDistanceByPath(node2, node3));
    assertEquals(6, NetworkTopology.getDistanceByPath(node2, node4));
  }

}