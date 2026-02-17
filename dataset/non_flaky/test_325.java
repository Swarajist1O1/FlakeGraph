class DummyClass_325 {
  @Test
  public void testNumExports() throws Exception {
    Assert.assertEquals(mountd.getExports().size(),
        viewFs.getChildFileSystems().length);
  }

}