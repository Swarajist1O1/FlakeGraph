class DummyClass_89278 {
  @Test
   public void testPostJobs()
      throws IOException {
    Response resp = target("v1/jobs").request().post(Entity.text(""));
    assertEquals(405, resp.getStatus());
    resp.close();
  }

}