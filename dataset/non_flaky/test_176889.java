class DummyClass_176889 {
  @Test
  public void testRedact() {
    String redacted = ConfigUtils.redact("  password=foo \nPassword=foo\nPASSWORD = foo\n" +
                                             " the-password= foo \nThe-Password =foo");
    assertFalse(redacted.contains("foo"));
    assertTrue(redacted.contains("*****"));
    assertTrue(redacted.contains("password="));
    assertTrue(redacted.contains("Password="));
    assertTrue(redacted.contains("PASSWORD = "));
    assertTrue(redacted.contains("the-password= "));
    assertTrue(redacted.contains("The-Password ="));
  }

}