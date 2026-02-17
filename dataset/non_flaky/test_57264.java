class DummyClass_57264 {
  @Test
  public void testUtility() {
    String[] names = NSSummaryEndpoint.parseRequestPath(TEST_PATH_UTILITY);
    Assert.assertArrayEquals(TEST_NAMES, names);
    String keyName = NSSummaryEndpoint.getKeyName(names);
    Assert.assertEquals(TEST_KEY_NAMES, keyName);
    String subpath = NSSummaryEndpoint.buildSubpath(PARENT_DIR, "file1.txt");
    Assert.assertEquals(TEST_PATH_UTILITY, subpath);
  }

}