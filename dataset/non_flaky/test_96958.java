class DummyClass_96958 {
  @Test
  public void testGetDeserializerForValue() throws IOException {
    // Set the reader schema in the job configuration.
    Schema readerSchema = Schema.create(Schema.Type.STRING);
    Job job = new Job();
    AvroJob.setMapOutputValueSchema(job, readerSchema);

    // Get a deserializer from the configuration.
    AvroSerialization serialization
        = ReflectionUtils.newInstance(AvroSerialization.class, job.getConfiguration());
    @SuppressWarnings("unchecked")
    Deserializer<AvroWrapper> deserializer = serialization.getDeserializer(AvroValue.class);
    assertTrue(deserializer instanceof AvroValueDeserializer);
    AvroValueDeserializer avroDeserializer = (AvroValueDeserializer) deserializer;

    // Check that the reader schema is set correctly on the deserializer.
    assertEquals(readerSchema, avroDeserializer.getReaderSchema());
  }

}