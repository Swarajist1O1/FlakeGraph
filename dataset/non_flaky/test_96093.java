class DummyClass_96093 {
  @Test
  public void testBasicExample() throws ClassNotFoundException, IOException {
    // set up document
    CoreDocument sampleDocument = new CoreDocument(sampleText);
    // annotate
    pipeline.annotate(sampleDocument);
    // serialize
    ByteArrayOutputStream ks = new ByteArrayOutputStream();
    serializer.writeCoreDocument(sampleDocument, ks).close();
    // Read
    InputStream kis = new ByteArrayInputStream(ks.toByteArray());
    Pair<Annotation, InputStream> pair = serializer.read(kis);
    pair.second.close();
    Annotation readAnnotation = pair.first;
    kis.close();
    ProtobufAnnotationSerializerSlowITest.sameAsRead(sampleDocument.annotation(), readAnnotation);
  }

}