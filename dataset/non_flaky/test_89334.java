class DummyClass_89334 {
  @Test(expected = StreamValidationException.class)
  public void testValidateStreamDoesNotExist() {

    StreamSpec spec = new StreamSpec("testId", "testStreamNameExist", "testSystem", 8);

    systemAdmin().validateStream(spec);
  }

}