class DummyClass_175787 {
  @Test
  public void testRun_projectHasNoApplication()
      throws ProjectRepositoryException, InterruptedException {
    when(projectRepository.getAppEngineApplication(credential, "projectId"))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);
    assertNull(project.getAppEngine());

    queryJob.schedule();
    queryJob.join();

    verify(projectRepository).getAppEngineApplication(credential, "projectId");
    verify(isLatestQueryJob).apply(queryJob);
    verify(projectSelector).isDisposed();
    verify(projectSelection).isEmpty();
    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_NO_APPLICATION, EXPECTED_LINK);

    assertEquals(AppEngine.NO_APPENGINE_APPLICATION, project.getAppEngine());
  }

}