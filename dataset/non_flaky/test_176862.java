class DummyClass_176862 {
  @Test
  public void testLoadClass() {
    assertSame(ArrayList.class, ClassUtils.loadClass(ArrayList.class.getName()));
  }

}