class DummyClass_60923 {
  @Test
  public void testGetDependentFields()
  {
    List<String> dependentFields = fac.getDependentFields();
    Assert.assertEquals(1, dependentFields.size());
    Assert.assertEquals("field", dependentFields.get(0));
  }

}