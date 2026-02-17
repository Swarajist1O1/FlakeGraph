class DummyClass_33719 {
  @Test
  public void isDigitFirst2() throws Throwable {
    // Arrange
    char ch = ':';
    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.JSONPath$JSONPathParser");
    Method m = c.getDeclaredMethod("isDigitFirst", Reflector.forName("char"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, ch);
    // Assert result
    Assert.assertEquals(false, retval);
  }

}