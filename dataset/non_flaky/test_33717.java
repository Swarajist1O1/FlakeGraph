class DummyClass_33717 {
  @Test
  public void eq2() throws Throwable {
    // Arrange
    Object a = null;
    Object b = null;
    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.JSONPath");
    Method m = c.getDeclaredMethod("eq", Reflector.forName("java.lang.Object"), Reflector.forName("java.lang.Object"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, a, b);
    // Assert result
    Assert.assertEquals(true, retval);
  }

}