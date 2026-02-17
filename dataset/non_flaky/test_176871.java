class DummyClass_176871 {
  @Test
  public void testReadWrite() throws Exception {
    Path tempModelFile = Files.createTempFile(getTempDir(), "model", ".pmml.gz");
    PMML model = buildDummyModel();
    PMMLUtils.write(model, tempModelFile);
    assertTrue(Files.exists(tempModelFile));
    PMML model2 = PMMLUtils.read(tempModelFile);
    List<Model> models = model2.getModels();
    assertEquals(1, models.size());
    assertTrue(models.get(0) instanceof TreeModel);
    TreeModel treeModel = (TreeModel) models.get(0);
    assertEquals(123.0, treeModel.getNode().getRecordCount().doubleValue());
    assertEquals(MiningFunctionType.CLASSIFICATION, treeModel.getFunctionName());
  }

}