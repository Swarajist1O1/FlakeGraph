class DummyClass_77019 {
  @Test
  public void getStorageIndexTest() {
    int index = ShuffleStorageUtils.getStorageIndex(3, "abcde", 3, 1);
    assertEquals(2, index);
    index = ShuffleStorageUtils.getStorageIndex(3, "abcde", 3, 4);
    assertEquals(1, index);
  }

}