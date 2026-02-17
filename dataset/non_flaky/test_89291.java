class DummyClass_89291 {
  @Test
  public void testGetTasksWithInvalidJobId() throws IOException {
    String requestUrl = String.format("v1/jobs/%s/%s/tasks", MockJobProxy.JOB_INSTANCE_1_NAME, "BadJobId");
    Response resp = target(requestUrl).request().get();
    assertEquals(400, resp.getStatus());
    final Map<String, String> errorMessage = objectMapper.readValue(resp.readEntity(String.class), new TypeReference<Map<String, String>>() { });
    assertTrue(errorMessage.get("message"), errorMessage.get("message").contains("Invalid arguments for getTasks. "));
    resp.close();
  }

}