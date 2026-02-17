class DummyClass_286 {
  @Test
  public void testReaddirBasic() throws IOException {
    // Get inodeId of /tmp
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);

    // Create related part of the XDR request
    XDR xdr_req = new XDR();
    FileHandle handle = new FileHandle(dirId, namenodeId);
    handle.serialize(xdr_req);
    xdr_req.writeLongAsHyper(0); // cookie
    xdr_req.writeLongAsHyper(0); // verifier
    xdr_req.writeInt(100); // count

    READDIR3Response response = nfsd.readdir(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));
    List<Entry3> dirents = response.getDirList().getEntries();
    assertTrue(dirents.size() == 5); // inculding dot, dotdot

    // Test start listing from f2
    status = nn.getRpcServer().getFileInfo(testdir + "/f2");
    long f2Id = status.getFileId();

    // Create related part of the XDR request
    xdr_req = new XDR();
    handle = new FileHandle(dirId, namenodeId);
    handle.serialize(xdr_req);
    xdr_req.writeLongAsHyper(f2Id); // cookie
    xdr_req.writeLongAsHyper(0); // verifier
    xdr_req.writeInt(100); // count

    response = nfsd.readdir(xdr_req.asReadOnlyWrap(), securityHandler,
        new InetSocketAddress("localhost", 1234));
    dirents = response.getDirList().getEntries();
    assertTrue(dirents.size() == 1);
    Entry3 entry = dirents.get(0);
    assertTrue(entry.getName().equals("f3"));

    // When the cookie is deleted, list starts over no including dot, dotdot
    hdfs.delete(new Path(testdir + "/f2"), false);

    response = nfsd.readdir(xdr_req.asReadOnlyWrap(), securityHandler,
        new InetSocketAddress("localhost", 1234));
    dirents = response.getDirList().getEntries();
    assertTrue(dirents.size() == 2); // No dot, dotdot
  }

}