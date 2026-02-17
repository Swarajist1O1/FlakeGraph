class DummyClass_94662 {
  @Test
  public void invokeOptionalNonPublic() throws Exception {
    SubClass2 subClass2 = new SubClass2();
    assertFalse(NON_PUBLIC.isSupported(subClass2));
    assertErrorOnInvokeOptional(NON_PUBLIC, subClass2);
  }

}