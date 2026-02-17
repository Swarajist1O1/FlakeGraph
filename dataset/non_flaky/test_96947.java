class DummyClass_96947 {
  @Test
  public void testHadoopCodecFactoryGZip(){
    CodecFactory hadoopSnappyCodec = HadoopCodecFactory.fromHadoopString("org.apache.hadoop.io.compress.GZipCodec");
    CodecFactory avroSnappyCodec = CodecFactory.fromString("deflate");
    assertTrue(hadoopSnappyCodec.getClass().equals(avroSnappyCodec.getClass()));
  }

}