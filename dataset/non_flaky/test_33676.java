class DummyClass_33676 {
  @Test
  public void charArrayCompare3() throws Throwable {

    // Arrange
    String src = "!!!!!!!\"&&";
    int offset = 6;
    char[] dest = { '\u0000' };

    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.parser.JSONScanner");
    Method m = c.getDeclaredMethod("charArrayCompare", Reflector.forName("java.lang.String"), Reflector.forName("int"), Reflector.forName("char []"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, src, offset, dest);

    // Assert result
    Assert.assertEquals(false, retval);

  }

}