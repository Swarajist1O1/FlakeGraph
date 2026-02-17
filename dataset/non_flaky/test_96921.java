class DummyClass_96921 {
  @Test
  public void testIgnoreFilesWithoutExtension() throws Exception {
    fs.mkdirs(inputDir);
    Path avroFile = new Path(inputDir, "somefile.avro");
    Path textFile = new Path(inputDir, "someotherfile.txt");
    fs.create(avroFile).close();
    fs.create(textFile).close();

    FileInputFormat.setInputPaths(conf, inputDir);

    AvroInputFormat inputFormat = new AvroInputFormat();
    FileStatus[] statuses = inputFormat.listStatus(conf);
    Assert.assertEquals(1, statuses.length);
    Assert.assertEquals("somefile.avro", statuses[0].getPath().getName());

    conf.setBoolean(AvroInputFormat.IGNORE_FILES_WITHOUT_EXTENSION_KEY, false);
    statuses = inputFormat.listStatus(conf);
    Assert.assertEquals(2, statuses.length);
    Set<String> names = new HashSet<>();
    names.add(statuses[0].getPath().getName());
    names.add(statuses[1].getPath().getName());
    Assert.assertTrue(names.contains("somefile.avro"));
    Assert.assertTrue(names.contains("someotherfile.txt"));
  }

}