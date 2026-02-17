class DummyClass_96919 {
  @Test
  public void testGZipCodecUsingHadoopClass() {
    CodecFactory avroDeflateCodec = CodecFactory.fromString("deflate");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.GZipCodec");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroDeflateCodec.getClass());
  }

}