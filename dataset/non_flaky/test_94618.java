class DummyClass_94618 {
  @Test public void callsNotManagedByDispatcher() throws Exception {
  public Buffer gzip(String data) throws IOException {
    Buffer result = new Buffer();
    BufferedSink gzipSink = Okio.buffer(new GzipSink(result));
    gzipSink.writeUtf8(data);
    gzipSink.close();
    return result;
  }

}