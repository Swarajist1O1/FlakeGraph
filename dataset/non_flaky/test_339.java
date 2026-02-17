class DummyClass_339 {
  @Test
  public void testViewFsMultipleExportPoint() throws IOException {
    NfsConfiguration config = new NfsConfiguration();
    MiniDFSCluster cluster = null;
    String clusterName = RandomStringUtils.randomAlphabetic(10);

    String exportPoint = "/hdfs1,/hdfs2";
    config.setStrings(NfsConfigKeys.DFS_NFS_EXPORT_POINT_KEY, exportPoint);
    config.set(CommonConfigurationKeysPublic.FS_DEFAULT_NAME_KEY,
        FsConstants.VIEWFS_SCHEME + "://" + clusterName);
    // Use emphral port in case tests are running in parallel
    config.setInt("nfs3.mountd.port", 0);
    config.setInt("nfs3.server.port", 0);
    config.set("nfs.http.address", "0.0.0.0:0");

    try {
      cluster =
          new MiniDFSCluster.Builder(config).nnTopology(
              MiniDFSNNTopology.simpleFederatedTopology(2))
              .numDataNodes(2)
              .build();
      cluster.waitActive();
      DistributedFileSystem hdfs1 = cluster.getFileSystem(0);
      DistributedFileSystem hdfs2 = cluster.getFileSystem(1);
      cluster.waitActive();
      Path base1 = new Path("/user1");
      Path base2 = new Path("/user2");
      hdfs1.delete(base1, true);
      hdfs2.delete(base2, true);
      hdfs1.mkdirs(base1);
      hdfs2.mkdirs(base2);
      ConfigUtil.addLink(config, clusterName, "/hdfs1",
          hdfs1.makeQualified(base1).toUri());
      ConfigUtil.addLink(config, clusterName, "/hdfs2",
          hdfs2.makeQualified(base2).toUri());

      // Start nfs
      final Nfs3 nfsServer = new Nfs3(config);
      nfsServer.startServiceInternal(false);

      Mountd mountd = nfsServer.getMountd();
      RpcProgramMountd rpcMount = (RpcProgramMountd) mountd.getRpcProgram();
      assertTrue(rpcMount.getExports().size() == 2);

      String exportInMountd1 = rpcMount.getExports().get(0);
      assertTrue(exportInMountd1.equals("/hdfs1"));

      String exportInMountd2 = rpcMount.getExports().get(1);
      assertTrue(exportInMountd2.equals("/hdfs2"));

    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}