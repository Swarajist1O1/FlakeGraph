class DummyClass_96914 {
  @Test
  public void testBZip2CodecUsingAvroCodec() {
    CodecFactory avroBZip2Codec = CodecFactory.fromString("bzip2");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set(AvroJob.OUTPUT_CODEC, "bzip2");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroBZip2Codec.getClass());
  }

}