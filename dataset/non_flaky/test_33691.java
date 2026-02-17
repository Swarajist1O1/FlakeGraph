class DummyClass_33691 {
  @Test
  public void checkDate14() throws Throwable {

    // Arrange
    char y0 = '2';
    char y1 = '1';
    char y2 = '1';
    char y3 = '1';
    char M0 = '1';
    char M1 = '0';
    int d0 = 51;
    int d1 = -2147483600;

    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.parser.JSONScanner");
    Method m = c.getDeclaredMethod("checkDate", Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("int"), Reflector.forName("int"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(null, y0, y1, y2, y3, M0, M1, d0, d1);

    // Assert result
    Assert.assertEquals(false, retval);

  }

}