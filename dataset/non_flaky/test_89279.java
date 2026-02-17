class DummyClass_89279 {
  @Test
  public void testPutJobs()
      throws IOException {
    Response resp = target("v1/jobs").request().put(Entity.text(""));
    assertEquals(405, resp.getStatus());
    resp.close();
  }

}