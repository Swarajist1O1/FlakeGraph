class DummyClass_94682 {
  @Test public void abortAfterDetach() throws Exception {
  public void writeFile(File file, String content) throws Exception {
    BufferedSink sink = Okio.buffer(fileSystem.sink(file));
    sink.writeUtf8(content);
    sink.close();
  }

}