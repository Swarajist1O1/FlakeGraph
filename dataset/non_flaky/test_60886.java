class DummyClass_60886 {
  @Test
  public void testNullBaseDataSource()
  {
    expectedException.expect(CoreMatchers.instanceOf(IllegalArgumentException.class));
    expectedException.expectMessage(
        "baseDataSource cannot be null or empty. Please provide a baseDataSource."
    );
    String baseDataSource = null;
    Set<String> dims = Sets.newHashSet("dim1", "dim2", "dim3");
    Set<String> metrics = Sets.newHashSet("cost");
    DerivativeDataSourceMetadata metadata = new DerivativeDataSourceMetadata(baseDataSource, dims, metrics);
  }

}