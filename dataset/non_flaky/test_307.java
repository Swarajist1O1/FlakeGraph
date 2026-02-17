class DummyClass_307 {
  @Test(timeout = 60000)
  public void testCreate() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    XDR xdr_req = new XDR();
    FileHandle handle = new FileHandle(dirId, namenodeId);
    CREATE3Request req = new CREATE3Request(handle, "fubar",
        Nfs3Constant.CREATE_UNCHECKED, new SetAttr3(), 0);
    req.serialize(xdr_req);
    
    // Attempt by an unpriviledged user should fail.
    CREATE3Response response1 = nfsd.create(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    CREATE3Response response2 = nfsd.create(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}