class DummyClass_150201 {
  @Test
  public void testGetModelForSystemWhenSetTo32() throws Exception {
    System.setProperty(DATA_MODEL_PROPERTY, "32");
    assertSame(JavaDataModel.JAVA32, JavaDataModel.getModelForSystem());
  }

}