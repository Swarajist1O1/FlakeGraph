class DummyClass_35687 {
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidPartition() {
    TimeEventQueue<String, Integer> eventQueue = new TimeEventQueue<>(Collections.singleton(1));
    eventQueue.add("test", 1L, 10, 2, 0);
  }

}