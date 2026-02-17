class DummyClass_342 {
  @Test
  public void testHdfsInternalExportPoint() throws IOException {
    NfsConfiguration config = new NfsConfiguration();
    MiniDFSCluster cluster = null;

    String exportPoint = "/myexport1";
    config.setStrings(NfsConfigKeys.DFS_NFS_EXPORT_POINT_KEY, exportPoint);
    // Use emphral port in case tests are running in parallel
    config.setInt("nfs3.mountd.port", 0);
    config.setInt("nfs3.server.port", 0);
    config.set("nfs.http.address", "0.0.0.0:0");
    Path base = new Path(exportPoint);

    try {
      cluster = new MiniDFSCluster.Builder(config).numDataNodes(1).build();
      cluster.waitActive();
      DistributedFileSystem hdfs = cluster.getFileSystem(0);
      hdfs.delete(base, true);
      hdfs.mkdirs(base);

      // Start nfs
      final Nfs3 nfsServer = new Nfs3(config);
      nfsServer.startServiceInternal(false);

      Mountd mountd = nfsServer.getMountd();
      RpcProgramMountd rpcMount = (RpcProgramMountd) mountd.getRpcProgram();
      assertTrue(rpcMount.getExports().size() == 1);

      String exportInMountd = rpcMount.getExports().get(0);
      assertTrue(exportInMountd.equals(exportPoint));

    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}