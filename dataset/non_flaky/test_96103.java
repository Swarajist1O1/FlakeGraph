class DummyClass_96103 {
  @Test
  public void testCanWriteReadCleanXML() {
    try {
      AnnotationSerializer serializer = new ProtobufAnnotationSerializer();
      // Write
      Properties props = new Properties();
      props.setProperty("annotators", "tokenize,cleanxml");
      StanfordCoreNLP pipe = new StanfordCoreNLP(props);
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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}