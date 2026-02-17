class DummyClass_57199 {
  @Test
  public void testGetLastKnownDB() throws IOException {
    File newDir = folder.newFolder();

    File file1 = Paths.get(newDir.getAbsolutePath(), "valid_1")
        .toFile();
    String str = "File1 Contents";
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file1.getAbsoluteFile()), UTF_8));
    writer.write(str);
    writer.close();

    File file2 = Paths.get(newDir.getAbsolutePath(), "valid_2")
        .toFile();
    str = "File2 Contents";
    writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file2.getAbsoluteFile()), UTF_8));
    writer.write(str);
    writer.close();


    File file3 = Paths.get(newDir.getAbsolutePath(), "invalid_3")
        .toFile();
    str = "File3 Contents";
    writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file3.getAbsoluteFile()), UTF_8));
    writer.write(str);
    writer.close();

    ReconUtils reconUtils = new ReconUtils();
    File latestValidFile = reconUtils.getLastKnownDB(newDir, "valid");
    assertTrue(latestValidFile.getName().equals("valid_2"));
  }

}