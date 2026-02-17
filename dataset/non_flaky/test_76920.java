class DummyClass_76920 {
  @Test
  public void compressionTest() {
    List<Integer> testSizes = Lists.newArrayList(
        1, 1024, 128 * 1024, 512 * 1024, 1024 * 1024, 4 * 1024 * 1024);
    for (int size : testSizes) {
      singleTest(size);
    }
  }

}