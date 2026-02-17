class DummyClass_33709 {
  @Test
  public void checkTime15() throws Throwable {

    // Arrange
    JSONScanner objectUnderTest = ((JSONScanner)Reflector.getInstance("com.alibaba.fastjson.parser.JSONScanner"));
    objectUnderTest.hasSpecial = false;
    objectUnderTest.token = 0;
    objectUnderTest.locale = null;
    objectUnderTest.np = 0;
    objectUnderTest.features = 0;
    Reflector.setField(objectUnderTest, "text", "");
    objectUnderTest.calendar = null;
    objectUnderTest.matchStat = 0;
    objectUnderTest.bp = 0;
    Reflector.setField(objectUnderTest, "len", 0);
    objectUnderTest.stringDefaultValue = "";
    objectUnderTest.pos = 0;
    objectUnderTest.sp = 0;
    objectUnderTest.sbuf = null;
    objectUnderTest.ch = '\u0000';
    objectUnderTest.timeZone = null;
    objectUnderTest.eofPos = 0;
    char h0 = '1';
    char h1 = '9';
    char m0 = '4';
    char m1 = '3';
    char s0 = '1';
    char s1 = '\u0430';

    // Act
    Class<?> c = Reflector.forName("com.alibaba.fastjson.parser.JSONScanner");
    Method m = c.getDeclaredMethod("checkTime", Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"), Reflector.forName("char"));
    m.setAccessible(true);
    boolean retval = (Boolean)m.invoke(objectUnderTest, h0, h1, m0, m1, s0, s1);

    // Assert result
    Assert.assertEquals(false, retval);

  }

}