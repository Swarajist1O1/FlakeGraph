class DummyClass_98281 {
  @Test
  public void testDigest_Docker18() throws Exception {
    final String status = "some-image-tag: digest: " + digest + " size: 1234";
    assertEquals(digest, readMessage(status).digest());
  }

}