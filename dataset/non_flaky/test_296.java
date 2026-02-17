class DummyClass_296 {
  @Test
  public void testOverlappingWrites() throws IOException, InterruptedException {
    NfsConfiguration config = new NfsConfiguration();
    MiniDFSCluster cluster = null;
    RpcProgramNfs3 nfsd;
    final int bufSize = 32;
    SecurityHandler securityHandler = Mockito.mock(SecurityHandler.class);
    Mockito.when(securityHandler.getUser()).thenReturn(
        System.getProperty("user.name"));
    String currentUser = System.getProperty("user.name");
    config.set(
        DefaultImpersonationProvider.getTestProvider().
            getProxySuperuserGroupConfKey(currentUser),
        "*");
    config.set(
        DefaultImpersonationProvider.getTestProvider().
            getProxySuperuserIpConfKey(currentUser),
        "*");
    ProxyUsers.refreshSuperUserGroupsConfiguration(config);
    // Use emphral port in case tests are running in parallel
    config.setInt("nfs3.mountd.port", 0);
    config.setInt("nfs3.server.port", 0);

    try {
      cluster = new MiniDFSCluster.Builder(config).numDataNodes(1).build();
      cluster.waitActive();

      Nfs3 nfs3 = new Nfs3(config);
      nfs3.startServiceInternal(false);
      nfsd = (RpcProgramNfs3) nfs3.getRpcProgram();

      DFSClient dfsClient = new DFSClient(DFSUtilClient.getNNAddress(config),
          config);
      int namenodeId = Nfs3Utils.getNamenodeId(config);
      HdfsFileStatus status = dfsClient.getFileInfo("/");
      FileHandle rootHandle = new FileHandle(status.getFileId(), namenodeId);

      CREATE3Request createReq = new CREATE3Request(rootHandle,
          "overlapping-writes" + System.currentTimeMillis(),
          Nfs3Constant.CREATE_UNCHECKED, new SetAttr3(), 0);
      XDR createXdr = new XDR();
      createReq.serialize(createXdr);
      CREATE3Response createRsp = nfsd.create(createXdr.asReadOnlyWrap(),
          securityHandler, new InetSocketAddress("localhost", 1234));
      FileHandle handle = createRsp.getObjHandle();
      byte[] buffer = new byte[bufSize];
      for (int i = 0; i < bufSize; i++) {
        buffer[i] = (byte) i;
      }
      int[][] ranges = new int[][] {
          {0, 10},
          {5, 7},
          {5, 5},
          {10, 6},
          {18, 6},
          {20, 6},
          {28, 4},
          {16, 2},
          {25, 4}
      };
      for (int i = 0; i < ranges.length; i++) {
        int x[] = ranges[i];
        byte[] tbuffer = new byte[x[1]];
        for (int j = 0; j < x[1]; j++) {
          tbuffer[j] = buffer[x[0] + j];
        }
        WRITE3Request writeReq = new WRITE3Request(handle, (long)x[0], x[1],
            WriteStableHow.UNSTABLE, ByteBuffer.wrap(tbuffer));
        XDR writeXdr = new XDR();
        writeReq.serialize(writeXdr);
        nfsd.write(writeXdr.asReadOnlyWrap(), null, 1, securityHandler,
            new InetSocketAddress("localhost", 1234));
      }

      waitWrite(nfsd, handle, 60000);
      READ3Request readReq = new READ3Request(handle, 0, bufSize);
      XDR readXdr = new XDR();
      readReq.serialize(readXdr);
      READ3Response readRsp = nfsd.read(readXdr.asReadOnlyWrap(),
          securityHandler, new InetSocketAddress("localhost", config.getInt(
              NfsConfigKeys.DFS_NFS_SERVER_PORT_KEY,
              NfsConfigKeys.DFS_NFS_SERVER_PORT_DEFAULT)));

      assertTrue(Arrays.equals(buffer, readRsp.getData().array()));
    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}