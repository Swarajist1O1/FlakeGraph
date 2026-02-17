class DummyClass_89280 {
  @Test
  public void testGetJob()
      throws IOException {
    Response resp = target(String.format("v1/jobs/%s/%s", MockJobProxy.JOB_INSTANCE_2_NAME, MockJobProxy.JOB_INSTANCE_2_ID)).request().get();
    assertEquals(200, resp.getStatus());
    final Job job2 = objectMapper.readValue(resp.readEntity(String.class), Job.class);

    assertEquals(MockJobProxy.JOB_INSTANCE_2_NAME, job2.getJobName());
    assertEquals(MockJobProxy.JOB_INSTANCE_2_ID, job2.getJobId());
    assertStatusNotDefault(job2);
    resp.close();
  }

}