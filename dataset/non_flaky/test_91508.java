class DummyClass_91508 {
  @Test(expected = IllegalArgumentException.class)
  public void testWrapObjectThrowsIllegalArgumentExceptionUsingDateType() {
      KylinClient.wrapObject("OQ? PYC6BWm`kOE", Types.DATE);
  }

}