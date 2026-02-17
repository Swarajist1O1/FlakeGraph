class DummyClass_298 {
  @Test
  public void testHttpServer() throws Exception {
    Nfs3 nfs = new Nfs3(conf);
    nfs.startServiceInternal(false);
    RpcProgramNfs3 nfsd = (RpcProgramNfs3) nfs.getRpcProgram();
    Nfs3HttpServer infoServer = nfsd.getInfoServer();

    String urlRoot = infoServer.getServerURI().toString();

    // Check default servlets.
    String pageContents = DFSTestUtil.urlGet(new URL(urlRoot + "/jmx"));
    assertTrue("Bad contents: " + pageContents,
        pageContents.contains("java.lang:type="));
    System.out.println("pc:" + pageContents);

    int port = infoServer.getSecurePort();
    assertTrue("Can't get https port", port > 0);
  }

}