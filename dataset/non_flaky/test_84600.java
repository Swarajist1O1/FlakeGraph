class DummyClass_84600 {
  @Test
  public void testReadStringForRecordsHavingLengthLessThanMaxAllowedSize()
      throws IOException {
    int maxBufferSize = 2000;
    int extraMaxBufferSize = 1025;
    int recordSize = maxBufferSize + extraMaxBufferSize - 100;
    //Exception is not expected as record size is less than the allowed size
    BinaryInputArchive ia =
        getBinaryInputArchive(recordSize, maxBufferSize, extraMaxBufferSize);
    String s = ia.readString("");
    assertNotNull(s);
    assertEquals(recordSize, s.getBytes().length);
  }

}