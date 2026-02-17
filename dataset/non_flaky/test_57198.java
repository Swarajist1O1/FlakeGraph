class DummyClass_57198 {
  @Test
  public void testMakeHttpCall() throws Exception {
    String url = "http://localhost:9874/dbCheckpoint";
    File file1 = Paths.get(folder.getRoot().getPath(), "file1")
        .toFile();
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file1.getAbsoluteFile()), UTF_8));
    writer.write("File 1 Contents");
    writer.close();
    InputStream fileInputStream = new FileInputStream(file1);

    String contents;
    URLConnectionFactory connectionFactoryMock =
        mock(URLConnectionFactory.class);
    HttpURLConnection urlConnectionMock = mock(HttpURLConnection.class);
    when(urlConnectionMock.getInputStream()).thenReturn(fileInputStream);
    when(connectionFactoryMock.openConnection(any(URL.class), anyBoolean()))
        .thenReturn(urlConnectionMock);
    try (InputStream inputStream = new ReconUtils()
        .makeHttpCall(connectionFactoryMock, url, false).getInputStream()) {
      contents = IOUtils.toString(inputStream, Charset.defaultCharset());
    }

    assertEquals("File 1 Contents", contents);
  }

}