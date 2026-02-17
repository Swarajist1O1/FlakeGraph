class DummyClass_176881 {
  @Test
  public void testChooseFreePort() throws IOException {
    int freePort = IOUtils.chooseFreePort();
    assertTrue(freePort >= 1024 && freePort < 65536);
    try (ServerSocket socket = new ServerSocket(freePort, 0)) {
      assertEquals(freePort, socket.getLocalPort());
    }
  }

}