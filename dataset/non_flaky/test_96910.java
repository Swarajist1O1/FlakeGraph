class DummyClass_96910 {
  @Test
  public void withSchemaMigration() throws IOException {
    FullRecordV2 src = new FullRecordV2(true, 731, 87231, 38L, 54.2832F, "Hi there",
                                        ByteBuffer.wrap(Utf8.getBytesFor("Hello, world!")));
    Assert.assertTrue("Test schema must allow for custom coders.",
                      ((SpecificRecordBase)src).hasCustomCoders());

    ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
    Encoder e = EncoderFactory.get().directBinaryEncoder(out, null);
    DatumWriter<FullRecordV2> w = (DatumWriter<FullRecordV2>)MODEL.createDatumWriter(V2S);
    w.write(src, e);
    e.flush();

    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    Decoder d = DecoderFactory.get().directBinaryDecoder(in, null);
    DatumReader<FullRecordV1> r = (DatumReader<FullRecordV1>)MODEL.createDatumReader(V2S, V1S);
    FullRecordV1 dst = r.read(null, d);

    FullRecordV1 expected = new FullRecordV1(true, 87231, 731L, 54.2832F, 38.0, null,
                                             "Hello, world!");
    Assert.assertEquals(expected, dst);
  }

}