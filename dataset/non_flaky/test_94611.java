class DummyClass_94611 {
  @Test public void etagConditionCanBeNonAscii() throws Exception {
  public Buffer gzip(String data) throws IOException {
    Buffer result = new Buffer();
    BufferedSink sink = Okio.buffer(new GzipSink(result));
    sink.writeUtf8(data);
    sink.close();
    return result;
  }

}