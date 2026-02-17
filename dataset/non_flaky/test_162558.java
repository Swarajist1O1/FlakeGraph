class DummyClass_162558 {
  @Test
  public void testSimple() {
    PortAllocator portAllocator = getPortAllocator((port) -> true);
    int next = PortAllocator.RANGE_MIN + 1;
    for (int i = 0; i < 1000; i++) {
      Assertions.assertEquals(next, portAllocator.getPort());
      next++;
      if (next % PortAllocator.CHUNK_SIZE == 0) {
        next++;
      }
    }
    Assertions.assertEquals(next, portAllocator.getPorts(10));
    Assertions.assertEquals(12101, portAllocator.getPorts(PortAllocator.CHUNK_SIZE - 1));
    assertThatThrownBy(() -> portAllocator.getPorts(PortAllocator.CHUNK_SIZE + 1))
        .isInstanceOf(IllegalStateException.class);
  }

}