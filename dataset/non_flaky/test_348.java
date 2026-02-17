class DummyClass_348 {
  @Test
  public void testRacks() throws Exception {
    assertEquals(cluster.getNumOfRacks(), 6);
    assertTrue(cluster.isOnSameRack(dataNodes[0], dataNodes[1]));
    assertFalse(cluster.isOnSameRack(dataNodes[1], dataNodes[2]));
    assertTrue(cluster.isOnSameRack(dataNodes[2], dataNodes[3]));
    assertTrue(cluster.isOnSameRack(dataNodes[3], dataNodes[4]));
    assertFalse(cluster.isOnSameRack(dataNodes[4], dataNodes[5]));
    assertTrue(cluster.isOnSameRack(dataNodes[5], dataNodes[6]));
  }

}