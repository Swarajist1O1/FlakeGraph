class DummyClass_33720 {
  @Test
  public void isDigitFirst3() throws Throwable {
    // Arrange
    char ch = '\u0000';
    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.JSONPath$JSONPathParser");
    Method m = c.getDeclaredMethod("isDigitFirst", Reflector.forName("char"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, ch);
    // Assert result
    Assert.assertEquals(false, retval);
  }

}