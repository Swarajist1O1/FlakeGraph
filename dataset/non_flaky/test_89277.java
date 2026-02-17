class DummyClass_89277 {
  @Test
   public void testGetJobs()
      throws IOException {

    Response resp = target("v1/jobs").request().get();
    assertEquals(200, resp.getStatus());
    final Job[] jobs = objectMapper.readValue(resp.readEntity(String.class), Job[].class);
    assertEquals(4, jobs.length);

    assertEquals(MockJobProxy.JOB_INSTANCE_1_NAME, jobs[0].getJobName());
    assertEquals(MockJobProxy.JOB_INSTANCE_1_ID, jobs[0].getJobId());
    assertStatusNotDefault(jobs[0]);
    assertEquals(MockJobProxy.JOB_INSTANCE_2_NAME, jobs[1].getJobName());
    assertEquals(MockJobProxy.JOB_INSTANCE_2_ID, jobs[1].getJobId());
    assertStatusNotDefault(jobs[1]);
    assertEquals(MockJobProxy.JOB_INSTANCE_3_NAME, jobs[2].getJobName());
    assertEquals(MockJobProxy.JOB_INSTANCE_3_ID, jobs[2].getJobId());
    assertStatusNotDefault(jobs[2]);
    assertEquals(MockJobProxy.JOB_INSTANCE_4_NAME, jobs[3].getJobName());
    assertEquals(MockJobProxy.JOB_INSTANCE_4_ID, jobs[3].getJobId());
    assertStatusNotDefault(jobs[3]);
    resp.close();
  }

}