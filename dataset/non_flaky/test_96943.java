class DummyClass_96943 {
  @Test
  public void testReader() throws IOException {
    Configuration conf = new Configuration();
    SortedKeyValueFile.Writer.Options writerOptions = new SortedKeyValueFile.Writer.Options()
        .withKeySchema(Schema.create(Schema.Type.STRING))
        .withValueSchema(Schema.create(Schema.Type.STRING))
        .withConfiguration(conf)
        .withPath(new Path(mTempDir.getRoot().getPath(), "myfile"))
        .withIndexInterval(2);  // Index every other record.

    SortedKeyValueFile.Writer<CharSequence, CharSequence> writer
        = new SortedKeyValueFile.Writer<>(writerOptions);

    try {
      writer.append("apple", "Apple");  // Will be indexed.
      writer.append("banana", "Banana");
      writer.append("carrot", "Carrot");  // Will be indexed.
      writer.append("durian", "Durian");
    } finally {
      writer.close();
    }

    LOG.debug("Reading the file back using a reader...");
    SortedKeyValueFile.Reader.Options readerOptions = new SortedKeyValueFile.Reader.Options()
        .withKeySchema(Schema.create(Schema.Type.STRING))
        .withValueSchema(Schema.create(Schema.Type.STRING))
        .withConfiguration(conf)
        .withPath(new Path(mTempDir.getRoot().getPath(), "myfile"));

    SortedKeyValueFile.Reader<CharSequence, CharSequence> reader
        = new SortedKeyValueFile.Reader<>(readerOptions);

    try {
      assertEquals("Carrot", reader.get("carrot").toString());
      assertEquals("Banana", reader.get("banana").toString());
      assertNull(reader.get("a-vegetable"));
      assertNull(reader.get("beet"));
      assertNull(reader.get("zzz"));
    } finally {
      reader.close();
    }
  }

}