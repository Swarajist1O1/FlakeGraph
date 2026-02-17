class DummyClass_33677 {
  @Test
  public void charArrayCompare4() throws Throwable {

    // Arrange
    String src = "!\"&&&&&";
    int offset = 0;
    char[] dest = { };

    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.parser.JSONScanner");
    Method m = c.getDeclaredMethod("charArrayCompare", Reflector.forName("java.lang.String"), Reflector.forName("int"), Reflector.forName("char []"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, src, offset, dest);

    // Assert result
    Assert.assertEquals(true, retval);

  }

}