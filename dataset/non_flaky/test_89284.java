class DummyClass_89284 {
  @Test
  public void testGetJobNameWithoutId()
      throws IOException {
    Response resp = target(String.format("v1/jobs/%s", MockJobProxy.JOB_INSTANCE_2_NAME)).request().get();
    assertEquals(404, resp.getStatus());
    resp.close();
  }

}