class DummyClass_89287 {
  @Test
  public void testPutBadJobStatus()
      throws IOException {
    Response resp = target(String.format("v1/jobs/%s/%s", MockJobProxy.JOB_INSTANCE_2_NAME, MockJobProxy.JOB_INSTANCE_2_ID))
        .queryParam("status", "BADSTATUS").request().put(Entity.form(new Form()));
    assertEquals(400, resp.getStatus());

    final Map<String, String> errorMessage = objectMapper.readValue(resp.readEntity(String.class), new TypeReference<Map<String, String>>() { });
    assertTrue(errorMessage.get("message").contains("BADSTATUS"));
    resp.close();
  }

}