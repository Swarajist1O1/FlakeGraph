class DummyClass_96952 {
  @Test
  public void testSerialize() throws IOException {
    // Create a serializer.
    Schema writerSchema = Schema.create(Schema.Type.STRING);
    AvroSerializer<CharSequence> serializer = new AvroSerializer<>(writerSchema);

    // Check the writer schema.
    assertEquals(writerSchema, serializer.getWriterSchema());

    // Serialize two records, 'record1' and 'record2'.
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    serializer.open(outputStream);
    serializer.serialize(new AvroKey<>("record1"));
    serializer.serialize(new AvroKey<>("record2"));
    serializer.close();

    // Make sure the records were serialized correctly.
    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    Schema readerSchema = Schema.create(Schema.Type.STRING);
    DatumReader<CharSequence> datumReader = new GenericDatumReader<>(readerSchema);
    Decoder decoder = DecoderFactory.get().binaryDecoder(inputStream, null);
    CharSequence record = null;

    record = datumReader.read(record, decoder);
    assertEquals("record1", record.toString());

    record = datumReader.read(record, decoder);
    assertEquals("record2", record.toString());

    inputStream.close();
  }

}