class DummyClass_57196 {
  @Test
  public void testCreateTarFile() throws Exception {

    File tempSnapshotDir = null;
    FileInputStream fis = null;
    FileOutputStream fos = null;
    File tarFile = null;

    try {
      String testDirName = System.getProperty("java.io.tmpdir");
      if (!testDirName.endsWith("/")) {
        testDirName += "/";
      }
      testDirName += "TestCreateTarFile_Dir" + System.currentTimeMillis();
      tempSnapshotDir = new File(testDirName);
      tempSnapshotDir.mkdirs();

      File file = new File(testDirName + "/temp1.txt");
      OutputStreamWriter writer = new OutputStreamWriter(
          new FileOutputStream(file), UTF_8);
      writer.write("Test data 1");
      writer.close();

      file = new File(testDirName + "/temp2.txt");
      writer = new OutputStreamWriter(
          new FileOutputStream(file), UTF_8);
      writer.write("Test data 2");
      writer.close();

      tarFile = createTarFile(Paths.get(testDirName));
      Assert.assertNotNull(tarFile);

    } finally {
      org.apache.hadoop.io.IOUtils.closeStream(fis);
      org.apache.hadoop.io.IOUtils.closeStream(fos);
      FileUtils.deleteDirectory(tempSnapshotDir);
      FileUtils.deleteQuietly(tarFile);
    }
  }

}