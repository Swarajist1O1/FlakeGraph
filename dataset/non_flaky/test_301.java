class DummyClass_301 {
  @Test(timeout = 60000)
  public void testLookup() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);
    LOOKUP3Request lookupReq = new LOOKUP3Request(handle, "bar");
    XDR xdr_req = new XDR();
    lookupReq.serialize(xdr_req);

    // Attempt by an unpriviledged user should fail.
    LOOKUP3Response response1 = nfsd.lookup(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    LOOKUP3Response response2 = nfsd.lookup(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}