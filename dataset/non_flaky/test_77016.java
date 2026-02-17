class DummyClass_77016 {
  @Test
  public void testUploadFile() {
    FileOutputStream fileOut = null;
    DataOutputStream dataOut = null;
    try {
      TemporaryFolder tmpDir = new TemporaryFolder();
      tmpDir.create();
      File file = tmpDir.newFile("test");
      fileOut = new FileOutputStream(file);
      dataOut = new DataOutputStream(fileOut);
      byte[] buf = new byte[2096];
      new Random().nextBytes(buf);
      dataOut.write(buf);
      dataOut.close();
      fileOut.close();
      String path = HDFS_URI + "test";
      HdfsFileWriter writer = new HdfsFileWriter(new Path(path), conf);
      long size = ShuffleStorageUtils.uploadFile(file, writer, 1024);
      assertEquals(2096, size);
      size = ShuffleStorageUtils.uploadFile(file, writer, 100);
      assertEquals(2096, size);
      writer.close();
      tmpDir.delete();
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}