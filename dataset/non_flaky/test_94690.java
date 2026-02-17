class DummyClass_94690 {
  @Test public void emptyResponseHeaderNameFromCacheIsLenient() throws Exception {
  public Buffer gzip(String data) throws IOException {
    Buffer result = new Buffer();
    BufferedSink sink = Okio.buffer(new GzipSink(result));
    sink.writeUtf8(data);
    sink.close();
    return result;
  }

}