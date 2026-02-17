class DummyClass_343 {
  @Test
  public void testInvalidFsExport() throws IOException {
    NfsConfiguration config = new NfsConfiguration();
    MiniDFSCluster cluster = null;

    // Use emphral port in case tests are running in parallel
    config.setInt("nfs3.mountd.port", 0);
    config.setInt("nfs3.server.port", 0);
    config.set("nfs.http.address", "0.0.0.0:0");

    try {
      cluster = new MiniDFSCluster.Builder(config).numDataNodes(1).build();
      cluster.waitActive();
      config.set(CommonConfigurationKeysPublic.FS_DEFAULT_NAME_KEY,
          FsConstants.LOCAL_FS_URI.toString());

      exception.expect(FileSystemException.class);
      exception.
          expectMessage("Only HDFS is supported as underlyingFileSystem, "
              + "fs scheme:file");
      // Start nfs
      final Nfs3 nfsServer = new Nfs3(config);
      nfsServer.startServiceInternal(false);
    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}