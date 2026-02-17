class DummyClass_91571 {
  @Test
  public void testSplitReturningNonEmptyArray() {
      String[] stringArray = StringSplitter.split("Fc8!v~f?aQL", "Fc8!v~f?aQL");

      assertEquals(2, stringArray.length);
      assertEquals("", stringArray[0]);
      assertEquals("", stringArray[1]);
  }

}