class DummyClass_302 {
  @Test(timeout = 60000)
  public void testAccess() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo("/tmp/bar");
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);
    XDR xdr_req = new XDR();
    ACCESS3Request req = new ACCESS3Request(handle);
    req.serialize(xdr_req);

    // Attempt by an unpriviledged user should fail.
    ACCESS3Response response1 = nfsd.access(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    ACCESS3Response response2 = nfsd.access(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}