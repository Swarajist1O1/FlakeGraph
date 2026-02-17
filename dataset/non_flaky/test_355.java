class DummyClass_355 {
  @Test
  public void testChooseRandomExcludeAllNodes() {
    String scope = "~" + NodeBase.getPath(dataNodes[0]);
    Set<Node> excludedNodes = new HashSet<>();
    for (int i = 0; i < dataNodes.length; i++) {
      excludedNodes.add(dataNodes[i]);
    }
    Map<Node, Integer> frequency = pickNodesAtRandom(100, scope, excludedNodes);
    for (Node key : dataNodes) {
      // all nodes except the first should be more than zero
      assertTrue(frequency.get(key) == 0);
    }
  }

}