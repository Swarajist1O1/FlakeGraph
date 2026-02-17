class DummyClass_84599 {
  @Test
  public void testReadStringForRecordsHavingLengthMoreThanMaxAllowedSize() {
    int maxBufferSize = 2000;
    int extraMaxBufferSize = 1025;
    //this record size is more than the max allowed size
    int recordSize = maxBufferSize + extraMaxBufferSize + 100;
    BinaryInputArchive ia =
        getBinaryInputArchive(recordSize, maxBufferSize, extraMaxBufferSize);
    try {
      ia.readString("");
      fail("Should have thrown an IOException");
    } catch (IOException e) {
      assertTrue(e.getMessage().startsWith(BinaryInputArchive.UNREASONBLE_LENGTH),
              () -> "Not 'Unreasonable length' exception: " + e);
    }
  }

}