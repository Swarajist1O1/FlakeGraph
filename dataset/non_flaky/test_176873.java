class DummyClass_176873 {
  @Test
  public void testFromString() throws Exception {
    PMML model = buildDummyModel();
    PMML model2 = PMMLUtils.fromString(PMMLUtils.toString(model));
    assertEquals(model.getHeader().getApplication().getName(),
                 model2.getHeader().getApplication().getName());
    assertEquals(model.getModels().get(0).getFunctionName(),
                 model2.getModels().get(0).getFunctionName());
  }

}