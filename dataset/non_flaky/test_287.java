class DummyClass_287 {
  @Test
  public void testReaddirPlus() throws IOException {
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
    xdr_req.writeInt(100); // dirCount
    xdr_req.writeInt(1000); // maxCount

    READDIRPLUS3Response responsePlus = nfsd.readdirplus(xdr_req
        .asReadOnlyWrap(), securityHandler, new InetSocketAddress("localhost",
        1234));
    List<EntryPlus3> direntPlus = responsePlus.getDirListPlus().getEntries();
    assertTrue(direntPlus.size() == 5); // including dot, dotdot

    // Test start listing from f2
    status = nn.getRpcServer().getFileInfo(testdir + "/f2");
    long f2Id = status.getFileId();

    // Create related part of the XDR request
    xdr_req = new XDR();
    handle = new FileHandle(dirId, namenodeId);
    handle.serialize(xdr_req);
    xdr_req.writeLongAsHyper(f2Id); // cookie
    xdr_req.writeLongAsHyper(0); // verifier
    xdr_req.writeInt(100); // dirCount
    xdr_req.writeInt(1000); // maxCount

    responsePlus = nfsd.readdirplus(xdr_req.asReadOnlyWrap(), securityHandler,
        new InetSocketAddress("localhost", 1234));
    direntPlus = responsePlus.getDirListPlus().getEntries();
    assertTrue(direntPlus.size() == 1);
    EntryPlus3 entryPlus = direntPlus.get(0);
    assertTrue(entryPlus.getName().equals("f3"));

    // When the cookie is deleted, list starts over no including dot, dotdot
    hdfs.delete(new Path(testdir + "/f2"), false);

    responsePlus = nfsd.readdirplus(xdr_req.asReadOnlyWrap(), securityHandler,
        new InetSocketAddress("localhost", 1234));
    direntPlus = responsePlus.getDirListPlus().getEntries();
    assertTrue(direntPlus.size() == 2); // No dot, dotdot
  }

}