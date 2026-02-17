class DummyClass_76964 {
  @Test
  public void testGetHostIp() {
    try {
      String address = InetAddress.getLocalHost().getHostAddress();
      String realIp = RssUtils.getHostIp();
      assertNotEquals("127.0.0.1", realIp);
      if (!address.equals("127.0.0.1")) {
        assertEquals(address, realIp);
      }
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

}