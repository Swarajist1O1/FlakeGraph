class DummyClass_33721 {
  @Test
  public void isEOF1() throws Throwable {
    // Arrange
    Object objectUnderTest = Reflector.getInstance("com.alibaba.fastjson.JSONPath$JSONPathParser");
    Reflector.setField(objectUnderTest, "path", "");
    Reflector.setField(objectUnderTest, "pos", -2147483647);
    Reflector.setField(objectUnderTest, "level", 0);
    Reflector.setField(objectUnderTest, "ch", '\u0000');
    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.JSONPath$JSONPathParser");
    Method m = c.getDeclaredMethod("isEOF");
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(objectUnderTest);
    // Assert result
    Assert.assertEquals(false, retval);
  }

}