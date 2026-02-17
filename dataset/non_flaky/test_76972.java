class DummyClass_76972 {
  @Test
  public void shufflePartitionedBlockTest() {
    byte[] buf = new byte[3];
    new Random().nextBytes(buf);

    ShufflePartitionedBlock b1 = new ShufflePartitionedBlock(1, 1, 2, 3, 1, buf);
    assertEquals(1, b1.getLength());
    assertEquals(2, b1.getCrc());
    assertEquals(3, b1.getBlockId());

    ShufflePartitionedBlock b3 = new ShufflePartitionedBlock(1, 1, 2, 3, 3, buf);
    assertArrayEquals(buf, b3.getData());
  }

}