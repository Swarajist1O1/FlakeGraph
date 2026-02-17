class DummyClass_57276 {
  @Test
  public void testGetTaskTimes() {
    ReconTaskStatusDao reconTaskStatusDao = getDao(ReconTaskStatusDao.class);

    ReconTaskStatus reconTaskStatusRecord = new ReconTaskStatus(
        "Dummy_Task", System.currentTimeMillis(), 0L);
    reconTaskStatusDao.insert(reconTaskStatusRecord);

    List<ReconTaskStatus> resultList = new ArrayList<>();
    resultList.add(reconTaskStatusRecord);

    Response response = taskStatusService.getTaskTimes();

    List<ReconTaskStatus> responseList = (List<ReconTaskStatus>)
        response.getEntity();

    Assert.assertEquals(resultList.size(), responseList.size());
    for(ReconTaskStatus r : responseList) {
      Assert.assertEquals(reconTaskStatusRecord.getTaskName(), r.getTaskName());
      Assert.assertEquals(reconTaskStatusRecord.getLastUpdatedTimestamp(),
          r.getLastUpdatedTimestamp());
    }
  }

}