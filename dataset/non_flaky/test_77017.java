class DummyClass_77017 {
  @Test
  public void mergeSegmentsTest() {
    List<FileBasedShuffleSegment> segments = Lists.newArrayList(
        new FileBasedShuffleSegment(1, 0, 40, 0, 0, 0));
    List<DataFileSegment> fileSegments = ShuffleStorageUtils.mergeSegments("path", segments, 100);
    assertEquals(1, fileSegments.size());
    for (DataFileSegment seg : fileSegments) {
      assertEquals(0, seg.getOffset());
      assertEquals(40, seg.getLength());
      assertEquals("path", seg.getPath());
      List<BufferSegment> bufferSegments = seg.getBufferSegments();
      assertEquals(1, bufferSegments.size());
      assertEquals(new BufferSegment(1, 0, 40, 0, 0, 0), bufferSegments.get(0));
    }

    segments = Lists.newArrayList(
        new FileBasedShuffleSegment(1, 0, 40, 0, 0, 0),
        new FileBasedShuffleSegment(2, 40, 40, 0, 0, 0),
        new FileBasedShuffleSegment(3, 80, 20, 0, 0, 0));
    fileSegments = ShuffleStorageUtils.mergeSegments("path", segments, 100);
    assertEquals(1, fileSegments.size());
    for (DataFileSegment seg : fileSegments) {
      assertEquals(0, seg.getOffset());
      assertEquals(100, seg.getLength());
      assertEquals("path", seg.getPath());
      List<BufferSegment> bufferSegments = seg.getBufferSegments();
      assertEquals(3, bufferSegments.size());
      Set<Long> testedBlockIds = Sets.newHashSet();
      for (BufferSegment segment : bufferSegments) {
        if (segment.getBlockId() == 1) {
          assertTrue(segment.equals(new BufferSegment(1, 0, 40, 0, 0, 0)));
          testedBlockIds.add(1L);
        } else if (segment.getBlockId() == 2) {
          assertTrue(segment.equals(new BufferSegment(2, 40, 40, 0, 0, 0)));
          testedBlockIds.add(2L);
        } else if (segment.getBlockId() == 3) {
          assertTrue(segment.equals(new BufferSegment(3, 80, 20, 0, 0, 0)));
          testedBlockIds.add(3L);
        }
      }
      assertEquals(3, testedBlockIds.size());
    }

    segments = Lists.newArrayList(
        new FileBasedShuffleSegment(1, 0, 40, 0, 0, 0),
        new FileBasedShuffleSegment(2, 40, 40, 0, 0, 0),
        new FileBasedShuffleSegment(3, 80, 20, 0, 0, 0),
        new FileBasedShuffleSegment(4, 100, 20, 0, 0, 0));
    fileSegments = ShuffleStorageUtils.mergeSegments("path", segments, 100);
    assertEquals(2, fileSegments.size());
    boolean tested = false;
    for (DataFileSegment seg : fileSegments) {
      if (seg.getOffset() == 100) {
        tested = true;
        assertEquals(20, seg.getLength());
        assertEquals("path", seg.getPath());
        List<BufferSegment> bufferSegments = seg.getBufferSegments();
        assertEquals(1, bufferSegments.size());
        assertTrue(bufferSegments.get(0).equals(new BufferSegment(4, 0, 20, 0, 0, 0)));
      }
    }
    assertTrue(tested);

    segments = Lists.newArrayList(
        new FileBasedShuffleSegment(1, 0, 40, 0, 0, 0),
        new FileBasedShuffleSegment(2, 40, 40, 0, 0, 0),
        new FileBasedShuffleSegment(3, 80, 20, 0, 0, 0),
        new FileBasedShuffleSegment(4, 100, 20, 0, 0, 0),
        new FileBasedShuffleSegment(5, 120, 100, 0, 0, 0));
    fileSegments = ShuffleStorageUtils.mergeSegments("path", segments, 100);
    assertEquals(2, fileSegments.size());
    tested = false;
    for (DataFileSegment seg : fileSegments) {
      if (seg.getOffset() == 100) {
        tested = true;
        assertEquals(120, seg.getLength());
        assertEquals("path", seg.getPath());
        List<BufferSegment> bufferSegments = seg.getBufferSegments();
        assertEquals(2, bufferSegments.size());
        Set<Long> testedBlockIds = Sets.newHashSet();
        for (BufferSegment segment : bufferSegments) {
          if (segment.getBlockId() == 4) {
            assertTrue(segment.equals(new BufferSegment(4, 0, 20, 0, 0, 0)));
            testedBlockIds.add(4L);
          } else if (segment.getBlockId() == 5) {
            assertTrue(segment.equals(new BufferSegment(5, 20, 100, 0, 0, 0)));
            testedBlockIds.add(5L);
          }
        }
        assertEquals(2, testedBlockIds.size());
      }
    }
    assertTrue(tested);

    segments = Lists.newArrayList(
        new FileBasedShuffleSegment(1, 10, 40, 0, 0, 0),
        new FileBasedShuffleSegment(2, 80, 20, 0, 0, 0),
        new FileBasedShuffleSegment(3, 500, 120, 0, 0, 0),
        new FileBasedShuffleSegment(4, 700, 20, 0, 0, 0));
    fileSegments = ShuffleStorageUtils.mergeSegments("path", segments, 100);
    assertEquals(3, fileSegments.size());
    Set<Long> expectedOffset = Sets.newHashSet(10L, 500L, 700L);
    for (DataFileSegment seg : fileSegments) {
      if (seg.getOffset() == 10) {
        validResult(seg, 90, 1, 40, 2, 70);
        expectedOffset.remove(10L);
      }
      if (seg.getOffset() == 500) {
        assertEquals(120, seg.getLength());
        List<BufferSegment> bufferSegments = seg.getBufferSegments();
        assertEquals(1, bufferSegments.size());
        assertTrue(bufferSegments.get(0).equals(new BufferSegment(3, 0, 120, 0, 0, 0)));
        expectedOffset.remove(500L);
      }
      if (seg.getOffset() == 700) {
        assertEquals(20, seg.getLength());
        List<BufferSegment> bufferSegments = seg.getBufferSegments();
        assertEquals(1, bufferSegments.size());
        assertTrue(bufferSegments.get(0).equals(new BufferSegment(4, 0, 20, 0, 0, 0)));
        expectedOffset.remove(700L);
      }
    }
    assertTrue(expectedOffset.isEmpty());

    segments = Lists.newArrayList(
        new FileBasedShuffleSegment(5, 500, 120, 0, 0, 0),
        new FileBasedShuffleSegment(3, 630, 10, 0, 0, 0),
        new FileBasedShuffleSegment(2, 80, 20, 0, 0, 0),
        new FileBasedShuffleSegment(1, 10, 40, 0, 0, 0),
        new FileBasedShuffleSegment(6, 769, 20, 0, 0, 0),
        new FileBasedShuffleSegment(4, 700, 20, 0, 0, 0));
    fileSegments = ShuffleStorageUtils.mergeSegments("path", segments, 100);
    assertEquals(4, fileSegments.size());
    expectedOffset = Sets.newHashSet(10L, 500L, 630L, 700L);
    for (DataFileSegment seg : fileSegments) {
      if (seg.getOffset() == 10) {
        validResult(seg, 90, 1, 40, 2, 70);
        expectedOffset.remove(10L);
      }
      if (seg.getOffset() == 500) {
        assertEquals(120, seg.getLength());
        List<BufferSegment> bufferSegments = seg.getBufferSegments();
        assertEquals(1, bufferSegments.size());
        assertTrue(bufferSegments.get(0).equals(new BufferSegment(5, 0, 120, 0, 0, 0)));
        expectedOffset.remove(500L);
      }
      if (seg.getOffset() == 630) {
        assertEquals(10, seg.getLength());
        List<BufferSegment> bufferSegments = seg.getBufferSegments();
        assertEquals(1, bufferSegments.size());
        assertTrue(bufferSegments.get(0).equals(new BufferSegment(3, 0, 10, 0, 0, 0)));
        expectedOffset.remove(630L);
      }
      if (seg.getOffset() == 700) {
        validResult(seg, 89, 4, 20, 6, 69);
        expectedOffset.remove(700L);
      }
    }
    assertTrue(expectedOffset.isEmpty());
  }

}