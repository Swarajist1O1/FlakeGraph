class DummyClass_35685 {
  @Test
  public void testOrdering() {
    TimeEventQueue<TimestampedEvent, Integer> eventQueue = new TimeEventQueue<>(ImmutableSet.of(1, 3));
    List<TimestampedEvent> expected = new ArrayList<>();

    // Put 10 events to partition 1, with both increasing timestamps and offsets
    for (int i = 0; i < 10; i++) {
      TimestampedEvent event = new TimestampedEvent(i, "m1" + i);
      eventQueue.add(event, i, 10, 1, i);
      expected.add(event);
    }

    // Put 10 events to partition 3, with increasing offsets, but non-sorted, some duplicated timestamps
    for (int i = 0; i < 10; i++) {
      long timestamp = i % 3;
      TimestampedEvent event = new TimestampedEvent(timestamp, "m3" + i);
      eventQueue.add(event, timestamp, 10, 3, i);
      expected.add(event);
    }

    // Sort the expected result based on timestamp. Since Collections.sort is stable sort,
    // partition 1 events will always be before partition 3 if the timestamps are the same
    Collections.sort(expected, new Comparator<TimestampedEvent>() {
      @Override
      public int compare(TimestampedEvent o1, TimestampedEvent o2) {
        return Long.compare(o1.getTimestamp(), o2.getTimestamp());
      }

}