class DummyClass_353 {
  @Test
  public void testChooseRandomExcludedRack() {
    Map<Node, Integer> frequency = pickNodesAtRandom(100, "~" + "/d2", null);
    // all the nodes on the second rack should be zero
    for (int j = 0; j < dataNodes.length; j++) {
      int freq = frequency.get(dataNodes[j]);
      if (dataNodes[j].getNetworkLocation().startsWith("/d2")) {
        assertEquals(0, freq);
      } else {
        assertTrue(freq > 0);
      }
    }
  }

}