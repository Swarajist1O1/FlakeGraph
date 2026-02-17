class DummyClass_344 {
  @Test
  public void testStart() throws IOException {
    // Start minicluster
    NfsConfiguration config = new NfsConfiguration();
    MiniDFSCluster cluster = new MiniDFSCluster.Builder(config).numDataNodes(1)
        .build();
    cluster.waitActive();
    
    // Use emphral port in case tests are running in parallel
    config.setInt("nfs3.mountd.port", 0);
    config.setInt("nfs3.server.port", 0);
    
    int newTimeoutMillis = 1000; // 1s
    // Set the new portmap rpc timeout values and check
    config.setInt(NfsConfigKeys.NFS_UDP_CLIENT_PORTMAP_TIMEOUT_MILLIS_KEY,
                  newTimeoutMillis);
    assertTrue(config.getInt(
                      NfsConfigKeys.NFS_UDP_CLIENT_PORTMAP_TIMEOUT_MILLIS_KEY,
          0) == newTimeoutMillis);

    // Start nfs
    Nfs3 nfs3 = new Nfs3(config);
    nfs3.startServiceInternal(false);

    RpcProgramMountd mountd = (RpcProgramMountd) nfs3.getMountd()
        .getRpcProgram();
    mountd.nullOp(new XDR(), 1234, InetAddress.getByName("localhost"));
    assertTrue(mountd.getPortmapUdpTimeoutMillis() == newTimeoutMillis);
    RpcProgramNfs3 nfsd = (RpcProgramNfs3) nfs3.getRpcProgram();
    nfsd.nullProcedure();
    assertTrue(nfsd.getPortmapUdpTimeoutMillis() == newTimeoutMillis);
    
    cluster.shutdown();
  }

}