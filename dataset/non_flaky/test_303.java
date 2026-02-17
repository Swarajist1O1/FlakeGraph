class DummyClass_303 {
  @Test(timeout = 60000)
  public void testReadlink() throws Exception {
    // Create a symlink first.
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    XDR xdr_req = new XDR();
    FileHandle handle = new FileHandle(dirId, namenodeId);
    SYMLINK3Request req = new SYMLINK3Request(handle, "fubar", new SetAttr3(),
        "bar");
    req.serialize(xdr_req);
    
    SYMLINK3Response response = nfsd.symlink(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response.getStatus());

    // Now perform readlink operations.
    FileHandle handle2 = response.getObjFileHandle();
    XDR xdr_req2 = new XDR();
    READLINK3Request req2 = new READLINK3Request(handle2);
    req2.serialize(xdr_req2);

    // Attempt by an unpriviledged user should fail.
    READLINK3Response response1 = nfsd.readlink(xdr_req2.asReadOnlyWrap(),
        securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    READLINK3Response response2 = nfsd.readlink(xdr_req2.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3_OK,
        response2.getStatus());
  }

}