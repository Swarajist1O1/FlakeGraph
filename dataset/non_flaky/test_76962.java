class DummyClass_76962 {
  @Test
  public void crc32TestWithByteBuff() throws Exception {
    int length = 32 * 1024 * 1024;
    byte[] data = new byte[length];
    new Random().nextBytes(data);

    String tempDir = Files.createTempDirectory("rss").toString();
    File file = new File(tempDir, "crc_test.txt");
    file.createNewFile();
    file.deleteOnExit();

    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(data);
    }

    long expectedChecksum = ChecksumUtils.getCrc32(data);

    // test direct ByteBuffer
    Path path = Paths.get(file.getAbsolutePath());
    FileChannel fileChannel = FileChannel.open(path);
    ByteBuffer buffer = ByteBuffer.allocateDirect(length);
    int bytesRead = fileChannel.read(buffer);
    fileChannel.close();
    assertEquals(length, bytesRead);
    buffer.flip();
    assertEquals(expectedChecksum, ChecksumUtils.getCrc32(buffer));
    assertEquals(length, buffer.position());

    // test heap ByteBuffer
    path = Paths.get(file.getAbsolutePath());
    fileChannel = FileChannel.open(path);
    buffer = ByteBuffer.allocate(length);
    bytesRead = fileChannel.read(buffer);
    fileChannel.close();
    assertEquals(length, bytesRead);
    buffer.flip();
    assertEquals(expectedChecksum, ChecksumUtils.getCrc32(buffer));

  }

}