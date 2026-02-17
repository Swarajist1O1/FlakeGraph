class DummyClass_89281 {
  @Test
  public void testPostJob()
      throws IOException {
    Response resp = target(String.format("v1/jobs/%s/%s", MockJobProxy.JOB_INSTANCE_2_NAME, MockJobProxy.JOB_INSTANCE_2_ID)).request().post(
        Entity.text(""));
    assertEquals(405, resp.getStatus());
    resp.close();
  }

}