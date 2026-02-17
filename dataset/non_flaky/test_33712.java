class DummyClass_33712 {
  @Test
  public void info1() throws Throwable {

    // Arrange
    JSONScanner objectUnderTest = ((JSONScanner)Reflector.getInstance("com.alibaba.fastjson.parser.JSONScanner"));
    objectUnderTest.hasSpecial = false;
    objectUnderTest.token = 0;
    Locale locale = ((Locale)Reflector.getInstance("java.util.Locale"));
    objectUnderTest.locale = locale;
    objectUnderTest.np = 0;
    objectUnderTest.features = 0;
    Reflector.setField(objectUnderTest, "text", "((((");
    objectUnderTest.calendar = null;
    objectUnderTest.matchStat = 0;
    objectUnderTest.bp = 7;
    Reflector.setField(objectUnderTest, "len", 0);
    objectUnderTest.stringDefaultValue = "!!!!";
    objectUnderTest.pos = 0;
    objectUnderTest.sp = 0;
    char[] charArray = { '\u0000' };
    objectUnderTest.sbuf = charArray;
    objectUnderTest.ch = '\u0000';
    objectUnderTest.timeZone = null;
    objectUnderTest.eofPos = 0;

    // Act
    String retval = objectUnderTest.info();

    // Assert result
    Assert.assertEquals("pos 7, json : ((((", retval);

  }

}