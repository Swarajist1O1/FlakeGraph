class DummyClass_96909 {
  @Test
  public void withoutSchemaMigration() throws IOException {
    FullRecordV1 src = new FullRecordV1(true, 87231, 731L, 54.2832F, 38.321, "Hi there", null);
    Assert.assertTrue("Test schema must allow for custom coders.",
                      ((SpecificRecordBase)src).hasCustomCoders());

    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    Encoder e = EncoderFactory.get().directBinaryEncoder(out, null);
    DatumWriter<FullRecordV1> w = (DatumWriter<FullRecordV1>)MODEL.createDatumWriter(V1S);
    w.write(src, e);
    e.flush();

    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    Decoder d = DecoderFactory.get().directBinaryDecoder(in, null);
    DatumReader<FullRecordV1> r = (DatumReader<FullRecordV1>)MODEL.createDatumReader(V1S);
    FullRecordV1 dst = r.read(null, d);

    Assert.assertEquals(src, dst);
  }

}