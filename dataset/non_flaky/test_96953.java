class DummyClass_96953 {
  @Test
  public void testDeserialize() throws IOException {
    // Create a deserializer.
    Schema writerSchema = Schema.create(Schema.Type.STRING);
    Schema readerSchema = Schema.create(Schema.Type.STRING);
    ClassLoader classLoader = this.getClass().getClassLoader();
    AvroKeyDeserializer<CharSequence> deserializer =
        new AvroKeyDeserializer<>(writerSchema, readerSchema, classLoader);

    // Check the schemas.
    assertEquals(writerSchema, deserializer.getWriterSchema());
    assertEquals(readerSchema, deserializer.getReaderSchema());

    // Write some records to deserialize.
    DatumWriter<CharSequence> datumWriter = new GenericDatumWriter<>(writerSchema);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
    datumWriter.write("record1", encoder);
    datumWriter.write("record2", encoder);
    encoder.flush();

    // Deserialize the records.
    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    deserializer.open(inputStream);
    AvroWrapper<CharSequence> record = null;

    record = deserializer.deserialize(record);
    assertEquals("record1", record.datum().toString());

    record = deserializer.deserialize(record);
    assertEquals("record2", record.datum().toString());

    deserializer.close();
  }

}