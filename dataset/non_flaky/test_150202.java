class DummyClass_150202 {
  @Test
  public void testGetModelForSystemWhenSetTo64() throws Exception {
    System.setProperty(DATA_MODEL_PROPERTY, "64");
    assertSame(JavaDataModel.JAVA64, JavaDataModel.getModelForSystem());
  }

}