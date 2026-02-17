class DummyClass_98279 {
  @Test
  public void testNotADigest() throws Exception {
    assertNull(readMessage("not-a-digest").digest());
  }

}