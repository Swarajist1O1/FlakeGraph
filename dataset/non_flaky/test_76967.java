class DummyClass_76967 {
  @Test
  public void test() {
    try {
    final int status = -1;
    final String testExitMessage = "testExitMessage";
    try {
      ExitUtils.disableSystemExit();
      ExitUtils.terminate(status, testExitMessage, null, null);
      fail();
    } catch (ExitException e) {
      assertEquals(status, e.getStatus());
      assertEquals(testExitMessage, e.getMessage());
    }

    final Thread t = new Thread(null, () -> {
      throw new AssertionError("TestUncaughtException");
    }, "testThread");
    t.start();
    t.join();
  } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }

}