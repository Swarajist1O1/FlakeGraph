class DummyClass_96938 {
  @Test(expected=IllegalArgumentException.class)
  public void testWriteOutOfSortedOrder() throws IOException {
    LOG.debug("Writing some records to a SortedKeyValueFile...");

    Configuration conf = new Configuration();
    SortedKeyValueFile.Writer.Options options = new SortedKeyValueFile.Writer.Options()
        .withKeySchema(Schema.create(Schema.Type.STRING))
        .withValueSchema(Schema.create(Schema.Type.STRING))
        .withConfiguration(conf)
        .withPath(new Path(mTempDir.getRoot().getPath(), "myfile"))
        .withIndexInterval(2);  // Index every other record.

    SortedKeyValueFile.Writer<CharSequence, CharSequence> writer
        = new SortedKeyValueFile.Writer<>(options);

    Utf8 key = new Utf8();                        // re-use key, to test copied

    try {
      writer.append(key.set("banana"), "Banana");
      writer.append(key.set("apple"), "Apple");  // Ruh, roh!
    } finally {
      writer.close();
    }
  }

}