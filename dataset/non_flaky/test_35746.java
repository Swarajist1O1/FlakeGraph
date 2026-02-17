class DummyClass_35746 {
  @Test
  public void testDeployNTimes() throws Exception {
    // regression tests for race condition during multiple deploys.
    deploy(100);
  }

}