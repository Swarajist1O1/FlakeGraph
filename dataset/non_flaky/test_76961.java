class DummyClass_76961 {
  @Test
  public void crc32TestWithByte() {
    byte[] data = new byte[32 * 1024 * 1024];
    new Random().nextBytes(data);
    CRC32 crc32 = new CRC32();
    crc32.update(data);
    long expected = crc32.getValue();
    assertEquals(expected, ChecksumUtils.getCrc32(data));

    data = new byte[32 * 1024];
    new Random().nextBytes(data);
    crc32 = new CRC32();
    crc32.update(data);
    expected = crc32.getValue();
    assertEquals(expected, ChecksumUtils.getCrc32(data));
  }

}