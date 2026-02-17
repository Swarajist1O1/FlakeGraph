class DummyClass_94616 {
  @Test public void unexpectedExceptionSync() throws Exception {
          @Override public List<InetAddress> lookup(String hostname) {
            throw new RuntimeException("boom!");
          }

}