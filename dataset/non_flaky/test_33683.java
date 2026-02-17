class DummyClass_33683 {
  @Test
  public void checkDate6() throws Throwable {

    // Arrange
    char y0 = '\u0000';
    char y1 = '\u0000';
    char y2 = '\u0000';
    char y3 = '\u0000';
    char M0 = '\u0000';
    char M1 = '\u0000';
    int d0 = 0;
    int d1 = 0;

    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.parser.JSONScanner");
    Method m = c.getDeclaredMethod("checkDate", Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("int"), Reflector.forName("int"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, y0, y1, y2, y3, M0, M1, d0, d1);

    // Assert result
    Assert.assertEquals(false, retval);

  }

}