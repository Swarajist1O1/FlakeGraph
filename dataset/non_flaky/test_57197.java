class DummyClass_57197 {
  @Test
  public void testUntarCheckpointFile() throws Exception {

    File newDir = folder.newFolder();

    File file1 = Paths.get(newDir.getAbsolutePath(), "file1")
        .toFile();
    String str = "File1 Contents";
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file1.getAbsoluteFile()), UTF_8));
    writer.write(str);
    writer.close();

    File file2 = Paths.get(newDir.getAbsolutePath(), "file2")
        .toFile();
    str = "File2 Contents";
    writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file2.getAbsoluteFile()), UTF_8));
    writer.write(str);
    writer.close();

    //Create test tar file.
    File tarFile = createTarFile(newDir.toPath());
    File outputDir = folder.newFolder();
    new ReconUtils().untarCheckpointFile(tarFile, outputDir.toPath());

    assertTrue(outputDir.isDirectory());
    assertTrue(outputDir.listFiles().length == 2);
  }

}