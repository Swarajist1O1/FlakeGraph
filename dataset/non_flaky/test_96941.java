class DummyClass_96941 {
  @Test
  public void testBadCodec() throws IOException {
    LOG.debug("Using a bad codec for a SortedKeyValueFile...");

    try {
      SortedKeyValueFile.Writer.Options options =
          new SortedKeyValueFile.Writer.Options().withCodec("foobar");
    } catch (AvroRuntimeException e) {
        assertEquals("Unrecognized codec: foobar", e.getMessage());
    }
  }

}