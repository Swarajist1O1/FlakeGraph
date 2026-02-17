class DummyClass_57210 {
  @Test
  public void testRegisterTask() {
    String taskName = "Dummy_" + System.currentTimeMillis();
    DummyReconDBTask dummyReconDBTask =
        new DummyReconDBTask(taskName, DummyReconDBTask.TaskType.ALWAYS_PASS);
    reconTaskController.registerTask(dummyReconDBTask);
    assertTrue(reconTaskController.getRegisteredTasks().size() == 1);
    assertTrue(reconTaskController.getRegisteredTasks()
        .get(dummyReconDBTask.getTaskName()) == dummyReconDBTask);
  }

}