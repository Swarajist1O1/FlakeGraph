class DummyClass_150204 {
  @Test
  public void testGetModelForSystemWhenUndefined() throws Exception {
    System.clearProperty(DATA_MODEL_PROPERTY);
    assertSame(JavaDataModel.JAVA64, JavaDataModel.getModelForSystem());
  }

}