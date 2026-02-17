class DummyClass_176864 {
  @Test
  public void testLoadInstanceOf() {
    assertTrue(ClassUtils.loadInstanceOf(HashSet.class) instanceof HashSet);
  }

}