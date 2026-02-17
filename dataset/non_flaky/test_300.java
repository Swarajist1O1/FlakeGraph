class DummyClass_300 {
  @Test(timeout = 60000)
  public void testSetattr() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    XDR xdr_req = new XDR();
    FileHandle handle = new FileHandle(dirId, namenodeId);
    SetAttr3 symAttr = new SetAttr3(0, 1, 0, 0, null, null,
        EnumSet.of(SetAttrField.UID));
    SETATTR3Request req = new SETATTR3Request(handle, symAttr, false, null);
    req.serialize(xdr_req);

    // Attempt by an unprivileged user should fail.
    SETATTR3Response response1 = nfsd.setattr(xdr_req.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    SETATTR3Response response2 = nfsd.setattr(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}