class DummyClass_94658 {
  @Test
  public void invokeNonPublic() throws Exception {
    SubClass2 subClass2 = new SubClass2();
    assertFalse(NON_PUBLIC.isSupported(subClass2));
    assertErrorOnInvoke(NON_PUBLIC, subClass2);
  }

}