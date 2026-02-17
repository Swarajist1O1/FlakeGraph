class DummyClass_175789 {
  @Test
  public void testRun_queryError() throws ProjectRepositoryException, InterruptedException {
    when(projectRepository.getAppEngineApplication(credential, "projectId"))
        .thenThrow(new ProjectRepositoryException("testException"));

    queryJob.schedule();
    queryJob.join();

    verify(isLatestQueryJob).apply(queryJob);
    verify(projectSelector).isDisposed();
    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_EXCEPTION, null);

    assertNull(project.getAppEngine());
  }

}