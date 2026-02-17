class DummyClass_314 {
  @Test(timeout = 60000)
  public void testReaddirplus() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);
    XDR xdr_req = new XDR();
    READDIRPLUS3Request req = new READDIRPLUS3Request(handle, 0, 0, 3, 2);
    req.serialize(xdr_req);
    
    // Attempt by an unprivileged user should fail.
    READDIRPLUS3Response response1 = nfsd.readdirplus(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a privileged user should pass.
    READDIRPLUS3Response response2 = nfsd.readdirplus(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}