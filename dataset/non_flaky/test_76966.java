class DummyClass_76966 {
  @Test
  public void testShuffleIndexSegment() {
    ShuffleIndexResult shuffleIndexResult = new ShuffleIndexResult();
    List<ShuffleDataSegment> shuffleDataSegments =
        RssUtils.transIndexDataToSegments(shuffleIndexResult, 1000);
    assertTrue(shuffleDataSegments.isEmpty());

    int readBufferSize = 32;
    int totalLength = 0;
    List<BufferSegment> bufferSegments = Lists.newArrayList();
    int[] dataSegmentLength = new int[]{32, 16, 10, 32, 6};

    for (int i = 0; i < dataSegmentLength.length; ++i) {
      long offset = totalLength;
      int length = dataSegmentLength[i];
      bufferSegments.add(new BufferSegment(i, offset, length, i, i, i));
      totalLength += length;
    }

    // those 5 segment's data length are [32, 16, 10, 32, 6] so the index should be
    // split into 3 ShuffleDataSegment, which are [32, 16 + 10 + 32, 6]
    int expectedTotalSegmentNum = 3;
    ByteBuffer byteBuffer = ByteBuffer.allocate(5 * 40);

    for (BufferSegment bufferSegment : bufferSegments) {
      byteBuffer.putLong(bufferSegment.getOffset());
      byteBuffer.putInt(bufferSegment.getLength());
      byteBuffer.putInt(bufferSegment.getUncompressLength());
      byteBuffer.putLong(bufferSegment.getCrc());
      byteBuffer.putLong(bufferSegment.getBlockId());
      byteBuffer.putLong(bufferSegment.getTaskAttemptId());
    }

    byte[] data = byteBuffer.array();
    shuffleDataSegments = RssUtils.transIndexDataToSegments(new ShuffleIndexResult(data), readBufferSize);
    assertEquals(expectedTotalSegmentNum, shuffleDataSegments.size());

    assertEquals(0, shuffleDataSegments.get(0).getOffset());
    assertEquals(32, shuffleDataSegments.get(0).getLength());
    assertEquals(1, shuffleDataSegments.get(0).getBufferSegments().size());

    assertEquals(32, shuffleDataSegments.get(1).getOffset());
    assertEquals(58, shuffleDataSegments.get(1).getLength());
    assertEquals(3,shuffleDataSegments.get(1).getBufferSegments().size());

    assertEquals(90, shuffleDataSegments.get(2).getOffset());
    assertEquals(6, shuffleDataSegments.get(2).getLength());
    assertEquals(1, shuffleDataSegments.get(2).getBufferSegments().size());
  }

}