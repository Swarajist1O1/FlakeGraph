class DummyClass_338 {
  @Test
  public void testHdfsExportPoint() throws IOException {
    NfsConfiguration config = new NfsConfiguration();
    MiniDFSCluster cluster = null;

    // Use emphral port in case tests are running in parallel
    config.setInt("nfs3.mountd.port", 0);
    config.setInt("nfs3.server.port", 0);
    config.set("nfs.http.address", "0.0.0.0:0");

    try {
      cluster = new MiniDFSCluster.Builder(config).numDataNodes(1).build();
      cluster.waitActive();

      // Start nfs
      final Nfs3 nfsServer = new Nfs3(config);
      nfsServer.startServiceInternal(false);

      Mountd mountd = nfsServer.getMountd();
      RpcProgramMountd rpcMount = (RpcProgramMountd) mountd.getRpcProgram();
      assertTrue(rpcMount.getExports().size() == 1);

      String exportInMountd = rpcMount.getExports().get(0);
      assertTrue(exportInMountd.equals("/"));

    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}