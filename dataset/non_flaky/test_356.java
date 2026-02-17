class DummyClass_356 {
  @Test(timeout=180000)
  public void testInvalidNetworkTopologiesNotCachedInHdfs() throws Exception {
    // start a cluster
    Configuration conf = new HdfsConfiguration();
    MiniDFSCluster cluster = null;
    try {
      // bad rack topology
      String racks[] = { "/a/b", "/c" };
      String hosts[] = { "foo1.example.com", "foo2.example.com" };
      cluster = new MiniDFSCluster.Builder(conf).numDataNodes(2).
          racks(racks).hosts(hosts).build();
      cluster.waitActive();
      
      NamenodeProtocols nn = cluster.getNameNodeRpc();
      Assert.assertNotNull(nn);
      
      // Wait for one DataNode to register.
      // The other DataNode will not be able to register up because of the rack mismatch.
      DatanodeInfo[] info;
      while (true) {
        info = nn.getDatanodeReport(DatanodeReportType.LIVE);
        Assert.assertFalse(info.length == 2);
        if (info.length == 1) {
          break;
        }
        Thread.sleep(1000);
      }
      // Set the network topology of the other node to the match the network
      // topology of the node that came up.
      int validIdx = info[0].getHostName().equals(hosts[0]) ? 0 : 1;
      int invalidIdx = validIdx == 1 ? 0 : 1;
      StaticMapping.addNodeToRack(hosts[invalidIdx], racks[validIdx]);
      LOG.info("datanode " + validIdx + " came up with network location " + 
        info[0].getNetworkLocation());

      // Restart the DN with the invalid topology and wait for it to register.
      cluster.restartDataNode(invalidIdx);
      Thread.sleep(5000);
      while (true) {
        info = nn.getDatanodeReport(DatanodeReportType.LIVE);
        if (info.length == 2) {
          break;
        }
        if (info.length == 0) {
          LOG.info("got no valid DNs");
        } else if (info.length == 1) {
          LOG.info("got one valid DN: " + info[0].getHostName() +
              " (at " + info[0].getNetworkLocation() + ")");
        }
        Thread.sleep(1000);
      }
      Assert.assertEquals(info[0].getNetworkLocation(),
                          info[1].getNetworkLocation());
    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}