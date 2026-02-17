class DummyClass_98280 {
  @Test
  public void testDigest_Docker16() throws Exception {
    assertEquals(digest, readMessage("Digest: " + digest).digest());
  }

}