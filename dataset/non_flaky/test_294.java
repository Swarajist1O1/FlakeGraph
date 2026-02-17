class DummyClass_294 {
  @Test
  public void testWriteStableHow() throws IOException, InterruptedException {
    NfsConfiguration config = new NfsConfiguration();
    DFSClient client = null;
    MiniDFSCluster cluster = null;
    RpcProgramNfs3 nfsd;
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

    try {
      cluster = new MiniDFSCluster.Builder(config).numDataNodes(1).build();
      cluster.waitActive();
      client = new DFSClient(DFSUtilClient.getNNAddress(config), config);
      int namenodeId = Nfs3Utils.getNamenodeId(config);

      // Use emphral port in case tests are running in parallel
      config.setInt("nfs3.mountd.port", 0);
      config.setInt("nfs3.server.port", 0);
      
      // Start nfs
      Nfs3 nfs3 = new Nfs3(config);
      nfs3.startServiceInternal(false);
      nfsd = (RpcProgramNfs3) nfs3.getRpcProgram();

      HdfsFileStatus status = client.getFileInfo("/");
      FileHandle rootHandle = new FileHandle(status.getFileId(), namenodeId);
      // Create file1
      CREATE3Request createReq = new CREATE3Request(rootHandle, "file1",
          Nfs3Constant.CREATE_UNCHECKED, new SetAttr3(), 0);
      XDR createXdr = new XDR();
      createReq.serialize(createXdr);
      CREATE3Response createRsp = nfsd.create(createXdr.asReadOnlyWrap(),
          securityHandler, new InetSocketAddress("localhost", 1234));
      FileHandle handle = createRsp.getObjHandle();

      // Test DATA_SYNC
      byte[] buffer = new byte[10];
      for (int i = 0; i < 10; i++) {
        buffer[i] = (byte) i;
      }
      WRITE3Request writeReq = new WRITE3Request(handle, 0, 10,
          WriteStableHow.DATA_SYNC, ByteBuffer.wrap(buffer));
      XDR writeXdr = new XDR();
      writeReq.serialize(writeXdr);
      nfsd.write(writeXdr.asReadOnlyWrap(), null, 1, securityHandler,
          new InetSocketAddress("localhost", 1234));

      waitWrite(nfsd, handle, 60000);

      // Readback
      READ3Request readReq = new READ3Request(handle, 0, 10);
      XDR readXdr = new XDR();
      readReq.serialize(readXdr);
      READ3Response readRsp = nfsd.read(readXdr.asReadOnlyWrap(),
          securityHandler, new InetSocketAddress("localhost", 1234));

      assertTrue(Arrays.equals(buffer, readRsp.getData().array()));

      // Test FILE_SYNC

      // Create file2
      CREATE3Request createReq2 = new CREATE3Request(rootHandle, "file2",
          Nfs3Constant.CREATE_UNCHECKED, new SetAttr3(), 0);
      XDR createXdr2 = new XDR();
      createReq2.serialize(createXdr2);
      CREATE3Response createRsp2 = nfsd.create(createXdr2.asReadOnlyWrap(),
          securityHandler, new InetSocketAddress("localhost", 1234));
      FileHandle handle2 = createRsp2.getObjHandle();

      WRITE3Request writeReq2 = new WRITE3Request(handle2, 0, 10,
          WriteStableHow.FILE_SYNC, ByteBuffer.wrap(buffer));
      XDR writeXdr2 = new XDR();
      writeReq2.serialize(writeXdr2);
      nfsd.write(writeXdr2.asReadOnlyWrap(), null, 1, securityHandler,
          new InetSocketAddress("localhost", 1234));

      waitWrite(nfsd, handle2, 60000);

      // Readback
      READ3Request readReq2 = new READ3Request(handle2, 0, 10);
      XDR readXdr2 = new XDR();
      readReq2.serialize(readXdr2);
      READ3Response readRsp2 = nfsd.read(readXdr2.asReadOnlyWrap(),
          securityHandler, new InetSocketAddress("localhost", 1234));

      assertTrue(Arrays.equals(buffer, readRsp2.getData().array()));
      // FILE_SYNC should sync the file size
      status = client.getFileInfo("/file2");
      assertTrue(status.getLen() == 10);

    } finally {
      if (cluster != null) {
        cluster.shutdown();
      }
    }
  }

}