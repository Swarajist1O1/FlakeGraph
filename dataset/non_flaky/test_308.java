class DummyClass_308 {
  @Test(timeout = 60000)
  public void testMkdir() throws Exception {//FixME
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    XDR xdr_req = new XDR();
    FileHandle handle = new FileHandle(dirId, namenodeId);
    MKDIR3Request req = new MKDIR3Request(handle, "fubar1", new SetAttr3());
    req.serialize(xdr_req);
    
    // Attempt to mkdir by an unprivileged user should fail.
    MKDIR3Response response1 = nfsd.mkdir(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    XDR xdr_req2 = new XDR();
    MKDIR3Request req2 = new MKDIR3Request(handle, "fubar2", new SetAttr3());
    req2.serialize(xdr_req2);
    
    // Attempt to mkdir by a privileged user should pass.
    MKDIR3Response response2 = nfsd.mkdir(xdr_req2.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}