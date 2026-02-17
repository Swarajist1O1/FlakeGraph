class DummyClass_176865 {
  @Test
  public void testLoadInstanceOf2() {
    assertTrue(ClassUtils.loadInstanceOf(HashSet.class.getName(), Set.class) instanceof HashSet);
  }

}