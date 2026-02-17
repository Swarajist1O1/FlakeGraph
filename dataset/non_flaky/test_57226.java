class DummyClass_57226 {
  @Test
  public void testSchemaSetup() throws SQLException {
    assertNotNull(getInjector());
    assertNotNull(getConfiguration());
    assertNotNull(getDslContext());
    assertNotNull(getConnection());
    RECON_DAO_LIST.forEach(dao -> {
      assertNotNull(getDao(dao));
    });
    ReconTaskStatusDao dao = getDao(ReconTaskStatusDao.class);
    dao.insert(new ReconTaskStatus("TestTask", 1L, 2L));
    assertEquals(1, dao.findAll().size());

    int numRows = getDslContext().delete(RECON_TASK_STATUS).execute();
    assertEquals(1, numRows);
    assertEquals(0, dao.findAll().size());
  }

}