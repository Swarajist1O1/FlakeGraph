class DummyClass_96102 {
  @Test
  public void testCanWriteRead() {
    try {
      AnnotationSerializer serializer = new ProtobufAnnotationSerializer();
      // Write
      StanfordCoreNLP pipe = new StanfordCoreNLP(new Properties());
      Annotation doc = pipe.process(prideAndPrejudiceFirstBit);
      ByteArrayOutputStream ks = new ByteArrayOutputStream();
      serializer.write(doc, ks).close();

      // Read
      InputStream kis = new ByteArrayInputStream(ks.toByteArray());
      Pair<Annotation, InputStream> pair = serializer.read(kis);
      pair.second.close();
      Annotation readDoc = pair.first;
      kis.close();

      sameAsRead(doc, readDoc);
    } catch (Exception e) { throw new RuntimeException(e); }
  }

}