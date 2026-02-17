class DummyClass_89290 {
  @Test
  public void testGetTasksWithInvalidJobName() throws IOException {
    String requestUrl = String.format("v1/jobs/%s/%s/tasks", "BadJobName", MockJobProxy.JOB_INSTANCE_4_ID);
    Response resp = target(requestUrl).request().get();
    assertEquals(400, resp.getStatus());
    final Map<String, String> errorMessage = objectMapper.readValue(resp.readEntity(String.class), new TypeReference<Map<String, String>>() { });
    assertTrue(errorMessage.get("message"), errorMessage.get("message").contains("Invalid arguments for getTasks. "));
    resp.close();
  }

}