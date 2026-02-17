class DummyClass_150200 {
  @Test
  public void testGetDoesNotReturnNull() throws Exception {
    JavaDataModel model = JavaDataModel.get();
    assertNotNull(model);
  }

}