class DummyClass_33713 {
  @Test
  public void isEOF1() throws Throwable {

    // Arrange
    JSONScanner objectUnderTest = ((JSONScanner)Reflector.getInstance("com.alibaba.fastjson.parser.JSONScanner"));
    objectUnderTest.hasSpecial = false;
    objectUnderTest.token = 0;
    objectUnderTest.locale = null;
    objectUnderTest.np = 0;
    objectUnderTest.features = 0;
    Reflector.setField(objectUnderTest, "text", null);
    objectUnderTest.calendar = null;
    objectUnderTest.matchStat = 0;
    objectUnderTest.bp = 0;
    Reflector.setField(objectUnderTest, "len", 0);
    objectUnderTest.stringDefaultValue = null;
    objectUnderTest.pos = 0;
    objectUnderTest.sp = 0;
    objectUnderTest.sbuf = null;
    objectUnderTest.ch = '\u001a';
    objectUnderTest.timeZone = null;
    objectUnderTest.eofPos = 0;

    // Act
    boolean retval = objectUnderTest.isEOF();

    // Assert result
    Assert.assertEquals(true, retval);

  }

}