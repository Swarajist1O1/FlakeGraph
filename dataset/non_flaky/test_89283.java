class DummyClass_89283 {
  @Test
  public void testGetJobIdNotFound()
      throws IOException {
    Response resp = target(String.format("v1/jobs/%s/%s", MockJobProxy.JOB_INSTANCE_2_NAME, "BadJobId")).request().get();
    assertEquals(404, resp.getStatus());

    final Map<String, String> errorMessage = objectMapper.readValue(resp.readEntity(String.class), new TypeReference<Map<String, String>>() { });
    assertTrue(errorMessage.get("message"), errorMessage.get("message").contains("does not exist"));
    resp.close();
  }

}