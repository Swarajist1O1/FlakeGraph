class DummyClass_35688 {
  @Test (expected = IllegalStateException.class)
  public void testIllegalRemove() {
    TimeEventQueue<String, Integer> eventQueue = new TimeEventQueue<>(Collections.singleton(1));
    eventQueue.add("test", 1L, 10, 1, 0);
    Iterator<String> iterator = eventQueue.iterator();
    iterator.next();
    iterator.remove();
    iterator.remove();
  }

}