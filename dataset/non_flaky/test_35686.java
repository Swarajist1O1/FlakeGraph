class DummyClass_35686 {
  @Test
  public void testKafkaOffset() {
    TimeEventQueue<String, Integer> eventQueue = new TimeEventQueue<>(ImmutableSet.of(1, 2));

    // Insert 6 events, with timestamps going back and forth
    eventQueue.add("m7", 7L, 10, 1, 0);
    eventQueue.add("m5", 5L, 10, 2, 1);
    eventQueue.add("m10", 10L, 10, 1, 2);
    eventQueue.add("m11", 11L, 10, 2, 3);
    eventQueue.add("m2", 2L, 10, 1, 4);
    eventQueue.add("m8", 8L, 10, 2, 5);

    // Have 6 events of size 10, so total size should be 60
    Assert.assertEquals(60, eventQueue.getEventSize());

    // Events should be time ordered when getting from iterator
    TimeEventQueue.EventIterator<String, Integer> iterator = eventQueue.iterator();

    Assert.assertEquals("m2", iterator.next());
    Assert.assertEquals(1, iterator.getPartition());
    Assert.assertEquals(4, iterator.getOffset().intValue());
    iterator.remove();
    Assert.assertEquals(50, eventQueue.getEventSize());
    Assert.assertEquals(0, eventQueue.getSmallestOffset(1).intValue());
    Assert.assertEquals(1, eventQueue.getSmallestOffset(2).intValue());

    Assert.assertEquals("m5", iterator.next());
    Assert.assertEquals(2, iterator.getPartition());
    Assert.assertEquals(1, iterator.getOffset().intValue());
    iterator.remove();
    Assert.assertEquals(40, eventQueue.getEventSize());
    Assert.assertEquals(0, eventQueue.getSmallestOffset(1).intValue());
    Assert.assertEquals(3, eventQueue.getSmallestOffset(2).intValue());

    Assert.assertEquals("m7", iterator.next());
    Assert.assertEquals(1, iterator.getPartition());
    Assert.assertEquals(0, iterator.getOffset().intValue());
    iterator.remove();
    Assert.assertEquals(30, eventQueue.getEventSize());
    Assert.assertEquals(2, eventQueue.getSmallestOffset(1).intValue());
    Assert.assertEquals(3, eventQueue.getSmallestOffset(2).intValue());

    Assert.assertEquals("m8", iterator.next());
    Assert.assertEquals(2, iterator.getPartition());
    Assert.assertEquals(5, iterator.getOffset().intValue());
    iterator.remove();
    Assert.assertEquals(20, eventQueue.getEventSize());
    Assert.assertEquals(2, eventQueue.getSmallestOffset(1).intValue());
    Assert.assertEquals(3, eventQueue.getSmallestOffset(2).intValue());

    Assert.assertEquals("m10", iterator.next());
    Assert.assertEquals(1, iterator.getPartition());
    Assert.assertEquals(2, iterator.getOffset().intValue());
    iterator.remove();
    Assert.assertEquals(10, eventQueue.getEventSize());
    Assert.assertTrue(eventQueue.isEmpty(1));

    Assert.assertEquals("m11", iterator.next());
    Assert.assertEquals(2, iterator.getPartition());
    Assert.assertEquals(3, iterator.getOffset().intValue());
    iterator.remove();
    Assert.assertEquals(0, eventQueue.getEventSize());
    Assert.assertTrue(eventQueue.isEmpty(2));

    Assert.assertTrue(eventQueue.isEmpty());
  }

}