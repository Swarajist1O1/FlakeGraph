class DummyClass_150205 {
  @Test
  public void testSmallBufferReuse() {
    BytesColumnVector col = new BytesColumnVector();
    int smallWriteSize = 1024;
    int largeWriteSize = 1024 * 1024 * 2;

    int rowIdx = 0;
    int bytesWrittenToBytes1 = 0;
    col.reset();

    // Initial write (small value)
    byte[] bytes1 = writeToBytesColumnVector(rowIdx, col, smallWriteSize, (byte) 1);
    bytesWrittenToBytes1 += smallWriteSize;

    // Write a large value. This should use a different byte buffer
    rowIdx++;
    byte[] bytes2 = writeToBytesColumnVector(rowIdx, col, largeWriteSize, (byte) 2);
    assertFalse(bytes1 == bytes2);

    // Another small write. smallBuffer should be re-used for this write
    rowIdx++;
    byte[] bytes3 = writeToBytesColumnVector(rowIdx, col, smallWriteSize, (byte) 1);
    bytesWrittenToBytes1 += smallWriteSize;
    assertTrue(bytes1 == bytes3);

    // Write another large value. This should use a different byte buffer
    rowIdx++;
    byte[] bytes4 = writeToBytesColumnVector(rowIdx, col, largeWriteSize, (byte) 3);
    assertFalse(bytes1 == bytes4);
    assertFalse(bytes2 == bytes4);

    // Eventually enough small writes should result in another buffer getting created
    boolean gotNewBuffer = false;
    // Test is dependent on getting a new buffer within 1MB.
    // This may need to change as the implementation changes.
    for (int i = 0; i < 1024; ++i) {
      rowIdx++;
      byte[] currBytes = writeToBytesColumnVector(rowIdx, col, smallWriteSize, (byte) 1);
      if (currBytes == bytes1) {
        bytesWrittenToBytes1 += smallWriteSize;
      } else {
        gotNewBuffer = true;
        break;
      }
    }

    assertTrue(gotNewBuffer);

    // All small writes to the first buffer should be in contiguous memory
    for (int i = 0; i < bytesWrittenToBytes1; ++i) {
      assertEquals((byte) 1, bytes1[i]);
    }
  }

}