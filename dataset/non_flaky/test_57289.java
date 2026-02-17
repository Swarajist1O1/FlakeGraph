class DummyClass_57289 {
  @Test
  public void testGet() throws Exception {
    ReconDBProvider reconDBProvider = injector.getInstance(
        ReconDBProvider.class);
    assertNotNull(reconDBProvider.getDbStore());
  }

}