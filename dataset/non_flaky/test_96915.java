class DummyClass_96915 {
  @Test
  public void testDeflateCodecUsingHadoopClass() {
    CodecFactory avroDeflateCodec = CodecFactory.fromString("deflate");

    JobConf job = new JobConf();
    job.set("mapred.output.compress", "true");
    job.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.DeflateCodec");
    CodecFactory factory = AvroOutputFormat.getCodecFactory(job);
    assertNotNull(factory);
    assertEquals(factory.getClass(), avroDeflateCodec.getClass());
  }

}