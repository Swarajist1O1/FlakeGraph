class DummyClass_306 {
  @Test(timeout = 60000)
  public void testWrite() throws Exception {
    HdfsFileStatus status = nn.getRpcServer().getFileInfo("/tmp/bar");
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);
    FileHandle handle = new FileHandle(dirId, namenodeId);

    byte[] buffer = new byte[10];
    for (int i = 0; i < 10; i++) {
      buffer[i] = (byte) i;
    }

    WRITE3Request writeReq = new WRITE3Request(handle, 0, 10,
        WriteStableHow.DATA_SYNC, ByteBuffer.wrap(buffer));
    XDR xdr_req = new XDR();
    writeReq.serialize(xdr_req);

    // Attempt by an unpriviledged user should fail.
    WRITE3Response response1 = nfsd.write(xdr_req.asReadOnlyWrap(),
        null, 1, securityHandlerUnpriviledged,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect return code:", Nfs3Status.NFS3ERR_ACCES,
        response1.getStatus());

    // Attempt by a priviledged user should pass.
    WRITE3Response response2 = nfsd.write(xdr_req.asReadOnlyWrap(),
        null, 1, securityHandler,
        new InetSocketAddress("localhost", 1234));
    assertEquals("Incorrect response:", null, response2);
  }

}