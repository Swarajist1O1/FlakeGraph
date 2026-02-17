class DummyClass_94650 {
  @Test public void racingReaders() throws Exception {
      @Override public ByteString call() throws Exception {
        Buffer buffer = new Buffer();
        while (source.read(buffer, 16384) != -1) {
        }
        source.close();
        return buffer.readByteString();
      }

}