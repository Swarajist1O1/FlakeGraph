class DummyClass_96944 {
  @Test
  public void testHadoopCodecFactoryDeflate(){
    CodecFactory hadoopDeflateCodec = HadoopCodecFactory.fromHadoopString("org.apache.hadoop.io.compress.DeflateCodec");
    CodecFactory avroDeflateCodec = CodecFactory.fromString("deflate");
    assertTrue(hadoopDeflateCodec.getClass().equals(avroDeflateCodec.getClass()));
  }

}