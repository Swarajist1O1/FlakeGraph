class DummyClass_337 {
  @Test(timeout = 60000)
  public void testClientAccessPrivilegeForRemove() throws Exception {
    // Configure ro access for nfs1 service
    config.set("dfs.nfs.exports.allowed.hosts", "* ro");

    // Start nfs
    Nfs3 nfs = new Nfs3(config);
    nfs.startServiceInternal(false);

    RpcProgramNfs3 nfsd = (RpcProgramNfs3) nfs.getRpcProgram();

    // Create a remove request
    HdfsFileStatus status = nn.getRpcServer().getFileInfo(testdir);
    long dirId = status.getFileId();
    int namenodeId = Nfs3Utils.getNamenodeId(config);

    XDR xdr_req = new XDR();
    FileHandle handle = new FileHandle(dirId, namenodeId);
    handle.serialize(xdr_req);
    xdr_req.writeString("f1");

    // Remove operation
    REMOVE3Response response = nfsd.remove(xdr_req.asReadOnlyWrap(),
        securityHandler, new InetSocketAddress("localhost", 1234));

    // Assert on return code
    assertEquals("Incorrect return code", Nfs3Status.NFS3ERR_ACCES,
        response.getStatus());

  }

}