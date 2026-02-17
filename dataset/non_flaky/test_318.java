class DummyClass_318 {
  @Test(timeout = 60000)
  public void testCommit() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo("/tmp/bar");
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);
    XDR xdr_req = new XDR();
    COMMIT3Request req = new COMMIT3Request(handle, 0, 5);
    req.serialize(xdr_req);

    Channel ch = Mockito.mock(Channel.class);

    // Attempt by an unpriviledged user should fail.
    COMMIT3Response response1 = nfsd.commit(xdr_req.asReadOnlyWrap(),
        ch, 1, securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    COMMIT3Response response2 = nfsd.commit(xdr_req.asReadOnlyWrap(),
        ch, 1, securityHandler,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect COMMIT3Response:", null, response2);
  }

}