class DummyClass_35738 {
  @Test
  public void testCorrectNumberInClassPath() throws Exception {
    Assert.assertEquals(ExpectedNumberOfAuditPolicyPaths.EXPECTED_PATH_NUMBER, AUDIT_LOOK_UP.getNumberOfPaths());
  }

}