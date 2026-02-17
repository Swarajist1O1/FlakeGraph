class DummyClass_96956 {
  @Test
  public void testGetSerializerForValue() throws IOException {
    // Set the writer schema in the job configuration.
    Schema writerSchema = Schema.create(Schema.Type.STRING);
    Job job = new Job();
    AvroJob.setMapOutputValueSchema(job, writerSchema);

    // Get a serializer from the configuration.
    AvroSerialization serialization
        = ReflectionUtils.newInstance(AvroSerialization.class, job.getConfiguration());
    @SuppressWarnings("unchecked")
    Serializer<AvroWrapper> serializer = serialization.getSerializer(AvroValue.class);
    assertTrue(serializer instanceof AvroSerializer);
    AvroSerializer avroSerializer = (AvroSerializer) serializer;

    // Check that the writer schema is set correctly on the serializer.
    assertEquals(writerSchema, avroSerializer.getWriterSchema());
  }

}