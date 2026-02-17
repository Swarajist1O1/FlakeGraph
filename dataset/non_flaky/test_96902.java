class DummyClass_96902 {
  @Test
  public void writeTests() throws Exception {
    if (! "write".equals(TEST_MODE)) return;

    for (GenTest t : tests) {
      t.write();
    }
  }

}