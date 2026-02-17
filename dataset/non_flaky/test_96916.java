class DummyClass_96916 {
  @Test
  public void testDeflateCodecUsingAvroCodec() {
    CodecFactory avroDeflateCodec = CodecFactory.fromString("deflate");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set(AvroJob.OUTPUT_CODEC, "deflate");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroDeflateCodec.getClass());
  }

}