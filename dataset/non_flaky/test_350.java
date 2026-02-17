class DummyClass_350 {
  @Test
  public void testSortByDistance() throws Exception {
    DatanodeDescriptor[] testNodes = new DatanodeDescriptor[3];
    
    // array contains both local node & local rack node
    testNodes[0] = dataNodes[1];
    testNodes[1] = dataNodes[2];
    testNodes[2] = dataNodes[0];
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(dataNodes[0], testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[0]);
    assertTrue(testNodes[1] == dataNodes[1]);
    assertTrue(testNodes[2] == dataNodes[2]);

    // array contains both local node & local rack node & decommissioned node
    DatanodeDescriptor[] dtestNodes = new DatanodeDescriptor[5];
    dtestNodes[0] = dataNodes[8];
    dtestNodes[1] = dataNodes[12];
    dtestNodes[2] = dataNodes[11];
    dtestNodes[3] = dataNodes[9];
    dtestNodes[4] = dataNodes[10];
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(dataNodes[8], dtestNodes, dtestNodes.length - 2);
    assertTrue(dtestNodes[0] == dataNodes[8]);
    assertTrue(dtestNodes[1] == dataNodes[11]);
    assertTrue(dtestNodes[2] == dataNodes[12]);
    assertTrue(dtestNodes[3] == dataNodes[9]);
    assertTrue(dtestNodes[4] == dataNodes[10]);

    // array contains local node
    testNodes[0] = dataNodes[1];
    testNodes[1] = dataNodes[3];
    testNodes[2] = dataNodes[0];
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(dataNodes[0], testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[0]);
    assertTrue(testNodes[1] == dataNodes[1]);
    assertTrue(testNodes[2] == dataNodes[3]);

    // array contains local rack node
    testNodes[0] = dataNodes[5];
    testNodes[1] = dataNodes[3];
    testNodes[2] = dataNodes[1];
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(dataNodes[0], testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[1]);
    assertTrue(testNodes[1] == dataNodes[3]);
    assertTrue(testNodes[2] == dataNodes[5]);

    // array contains local rack node which happens to be in position 0
    testNodes[0] = dataNodes[1];
    testNodes[1] = dataNodes[5];
    testNodes[2] = dataNodes[3];
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(dataNodes[0], testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[1]);
    assertTrue(testNodes[1] == dataNodes[3]);
    assertTrue(testNodes[2] == dataNodes[5]);

    // Same as previous, but with a different random seed to test randomization
    testNodes[0] = dataNodes[1];
    testNodes[1] = dataNodes[5];
    testNodes[2] = dataNodes[3];
    cluster.setRandomSeed(0xDEAD);
    cluster.sortByDistance(dataNodes[0], testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[1]);
    assertTrue(testNodes[1] == dataNodes[3]);
    assertTrue(testNodes[2] == dataNodes[5]);

    // Array of just rack-local nodes
    // Expect a random first node
    DatanodeDescriptor first = null;
    boolean foundRandom = false;
    for (int i=5; i<=7; i++) {
      testNodes[0] = dataNodes[5];
      testNodes[1] = dataNodes[6];
      testNodes[2] = dataNodes[7];
      cluster.sortByDistance(dataNodes[i], testNodes, testNodes.length);
      if (first == null) {
        first = testNodes[0];
      } else {
        if (first != testNodes[0]) {
          foundRandom = true;
          break;
        }
      }
    }
    assertTrue("Expected to find a different first location", foundRandom);

    // Array of just remote nodes
    // Expect random first node
    first = null;
    for (int i = 1; i <= 4; i++) {
      testNodes[0] = dataNodes[13];
      testNodes[1] = dataNodes[14];
      testNodes[2] = dataNodes[15];
      cluster.sortByDistance(dataNodes[i], testNodes, testNodes.length);
      if (first == null) {
        first = testNodes[0];
      } else {
        if (first != testNodes[0]) {
          foundRandom = true;
          break;
        }
      }
    }
    assertTrue("Expected to find a different first location", foundRandom);

    //Reader is not a datanode, but is in one of the datanode's rack.
    testNodes[0] = dataNodes[0];
    testNodes[1] = dataNodes[5];
    testNodes[2] = dataNodes[8];
    Node rackClient = new NodeBase("/d3/r1/25.25.25");
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(rackClient, testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[8]);
    assertTrue(testNodes[1] == dataNodes[5]);
    assertTrue(testNodes[2] == dataNodes[0]);

    //Reader is not a datanode , but is in one of the datanode's data center.
    testNodes[0] = dataNodes[8];
    testNodes[1] = dataNodes[5];
    testNodes[2] = dataNodes[0];
    Node dcClient = new NodeBase("/d1/r2/25.25.25");
    cluster.setRandomSeed(0xDEADBEEF);
    cluster.sortByDistance(dcClient, testNodes, testNodes.length);
    assertTrue(testNodes[0] == dataNodes[0]);
    assertTrue(testNodes[1] == dataNodes[5]);
    assertTrue(testNodes[2] == dataNodes[8]);

  }

}