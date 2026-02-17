class DummyClass_162559 {
  @Test
  public void testEven() {
    PortAllocator portAllocator = getPortAllocator((port) -> port % 2 == 0);
    int next = PortAllocator.RANGE_MIN + 2;
    for (int i = 0; i < 1000; i++) {
      Assertions.assertEquals(next, portAllocator.getPort());
      next += 2;
      if (next % PortAllocator.CHUNK_SIZE == 0) {
        next += 2;
      }
    }
    assertThatThrownBy(() -> portAllocator.getPorts(2)).isInstanceOf(IllegalStateException.class);
  }

}