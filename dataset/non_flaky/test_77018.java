class DummyClass_77018 {
  @Test
  public void getShuffleDataPathWithRangeTest() {
    String result = ShuffleStorageUtils.getShuffleDataPathWithRange("appId", 0, 1, 3, 6);
    assertEquals("appId/0/0-2", result);
    result = ShuffleStorageUtils.getShuffleDataPathWithRange("appId", 0, 2, 3, 6);
    assertEquals("appId/0/0-2", result);
    result = ShuffleStorageUtils.getShuffleDataPathWithRange("appId", 0, 3, 3, 6);
    assertEquals("appId/0/3-5", result);
    result = ShuffleStorageUtils.getShuffleDataPathWithRange("appId", 0, 5, 3, 6);
    assertEquals("appId/0/3-5", result);
    try {
      ShuffleStorageUtils.getShuffleDataPathWithRange("appId", 0, 6, 3, 6);
      fail("shouldn't be here");
    } catch (Exception e) {
      assertTrue(e.getMessage().startsWith("Can't generate ShuffleData Path"));
    }
    result = ShuffleStorageUtils.getShuffleDataPathWithRange("appId", 0, 6, 3, 7);
    assertEquals("appId/0/6-8", result);
  }

}