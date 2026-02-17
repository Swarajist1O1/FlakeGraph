class DummyClass_305 {
  @Test(timeout = 120000)
  public void testEncryptedReadWrite() throws Exception {
    final int len = 8192;

    final Path zone = new Path("/zone");
    hdfs.mkdirs(zone);
    dfsAdmin.createEncryptionZone(zone, TEST_KEY, NO_TRASH);

    final byte[] buffer = new byte[len];
    for (int i = 0; i < len; i++) {
      buffer[i] = (byte) i;
    }

    final String encFile1 = "/zone/myfile";
    createFileUsingNfs(encFile1, buffer);
    commit(encFile1, len);
    assertArrayEquals("encFile1 not equal",
        getFileContentsUsingNfs(encFile1, len),
        getFileContentsUsingDfs(encFile1, len));

    /*
     * Same thing except this time create the encrypted file using DFS.
     */
    final String encFile2 = "/zone/myfile2";
    final Path encFile2Path = new Path(encFile2);
    DFSTestUtil.createFile(hdfs, encFile2Path, len, (short) 1, 0xFEED);
    assertArrayEquals("encFile2 not equal",
        getFileContentsUsingNfs(encFile2, len),
        getFileContentsUsingDfs(encFile2, len));
  }

}