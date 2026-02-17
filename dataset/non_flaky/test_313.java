class DummyClass_313 {
  @Test(timeout = 60000)
  public void testReaddir() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);
    XDR xdr_req = new XDR();
    READDIR3Request req = new READDIR3Request(handle, 0, 0, 100);
    req.serialize(xdr_req);

    // Attempt by an unpriviledged user should fail.
    READDIR3Response response1 = nfsd.readdir(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    READDIR3Response response2 = nfsd.readdir(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}