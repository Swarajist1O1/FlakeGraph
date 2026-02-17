class DummyClass_89289 {
  @Test
  public void testGetTasks() throws IOException {
    String requestUrl = String.format("v1/jobs/%s/%s/tasks", "testJobName", "testJobId");
    Response response = target(requestUrl).request().get();
    assertEquals(200, response.getStatus());
    Task[] tasks = objectMapper.readValue(response.readEntity(String.class), Task[].class);
    assertEquals(2, tasks.length);

    assertEquals(MockTaskProxy.TASK_1_PREFERRED_HOST, tasks[0].getPreferredHost());
    assertEquals(MockTaskProxy.TASK_1_CONTAINER_ID, tasks[0].getContainerId());
    assertEquals(MockTaskProxy.TASK_1_NAME, tasks[0].getTaskName());
    assertEquals(MockTaskProxy.PARTITIONS, tasks[0].getPartitions());

    assertEquals(MockTaskProxy.TASK_2_PREFERRED_HOST, tasks[1].getPreferredHost());
    assertEquals(MockTaskProxy.TASK_2_CONTAINER_ID, tasks[1].getContainerId());
    assertEquals(MockTaskProxy.TASK_2_NAME, tasks[1].getTaskName());
    assertEquals(MockTaskProxy.PARTITIONS, tasks[1].getPartitions());
  }

}