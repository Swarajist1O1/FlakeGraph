class DummyClass_354 {
  @Test
  public void testChooseRandomExcludedNodeList() {
    String scope = "~" + NodeBase.getPath(dataNodes[0]);
    Set<Node> excludedNodes = new HashSet<>();
    excludedNodes.add(dataNodes[3]);
    excludedNodes.add(dataNodes[5]);
    excludedNodes.add(dataNodes[7]);
    excludedNodes.add(dataNodes[9]);
    excludedNodes.add(dataNodes[13]);
    excludedNodes.add(dataNodes[18]);
    Map<Node, Integer> frequency = pickNodesAtRandom(100, scope, excludedNodes);

    assertEquals("dn[3] should be excluded", 0,
        frequency.get(dataNodes[3]).intValue());
    assertEquals("dn[5] should be exclude18d", 0,
        frequency.get(dataNodes[5]).intValue());
    assertEquals("dn[7] should be excluded", 0,
        frequency.get(dataNodes[7]).intValue());
    assertEquals("dn[9] should be excluded", 0,
        frequency.get(dataNodes[9]).intValue());
    assertEquals("dn[13] should be excluded", 0,
        frequency.get(dataNodes[13]).intValue());
    assertEquals("dn[18] should be excluded", 0,
        frequency.get(dataNodes[18]).intValue());
    for (Node key : dataNodes) {
      if (excludedNodes.contains(key)) {
        continue;
      }
      // all nodes except the first should be more than zero
      assertTrue(frequency.get(key) > 0 || key == dataNodes[0]);
    }
  }

}