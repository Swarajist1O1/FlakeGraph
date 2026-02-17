class DummyClass_94664 {
  @Test public void headersWithPriority() throws IOException {
      @Override public void priority(int streamId, int streamDependency, int weight,
          boolean exclusive) {
        assertEquals(0, streamDependency);
        assertEquals(256, weight);
        assertFalse(exclusive);
      }

}