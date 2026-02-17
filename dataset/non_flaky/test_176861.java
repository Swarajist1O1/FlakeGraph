class DummyClass_176861 {
  @Test
  public void testAutoClose() {
    ReentrantLock lock = new ReentrantLock();
    assertFalse(lock.isHeldByCurrentThread());
    try (AutoLock al = new AutoLock(lock)) {
      assertTrue(lock.isHeldByCurrentThread());
    }
    assertFalse(lock.isHeldByCurrentThread());
  }

}