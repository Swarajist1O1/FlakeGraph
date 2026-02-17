class DummyClass_33718 {
  @Test
  public void isDigitFirst1() throws Throwable {
    // Arrange
    char ch = '2';
    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.JSONPath$JSONPathParser");
    Method m = c.getDeclaredMethod("isDigitFirst", Reflector.forName("char"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, ch);
    // Assert result
    Assert.assertEquals(true, retval);
  }

}