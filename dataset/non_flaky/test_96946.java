class DummyClass_96946 {
  @Test
  public void testHadoopCodecFactoryBZip2(){
    CodecFactory hadoopSnappyCodec = HadoopCodecFactory.fromHadoopString("org.apache.hadoop.io.compress.BZip2Codec");
    CodecFactory avroSnappyCodec = CodecFactory.fromString("bzip2");
    assertTrue(hadoopSnappyCodec.getClass().equals(avroSnappyCodec.getClass()));
  }

}