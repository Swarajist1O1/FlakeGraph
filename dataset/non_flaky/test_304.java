class DummyClass_304 {
  @Test(timeout = 60000)
  public void testRead() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo("/tmp/bar");
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);

    READ3Request readReq = new READ3Request(handle, 0, 5);
    XDR xdr_req = new XDR();
    readReq.serialize(xdr_req);

    // Attempt by an unpriviledged user should fail.
    READ3Response response1 = nfsd.read(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    READ3Response response2 = nfsd.read(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}