class DummyClass_96104 {
  @Test
  public void testCanWriteReadWriteReadLargeFile() {
    try {
      AnnotationSerializer serializer = new ProtobufAnnotationSerializer();
      // Write
      StanfordCoreNLP pipe = new StanfordCoreNLP(new Properties());
      Annotation doc = pipe.process(prideAndPrejudiceChapters1to5);

      ByteArrayOutputStream ks = new ByteArrayOutputStream();
      serializer.write(doc, ks).close();

      // Read
      InputStream kis = new ByteArrayInputStream(ks.toByteArray());
      Pair<Annotation, InputStream> pair1 = serializer.read(kis);
      pair1.second.close();
      Annotation readDoc = pair1.first;
      kis.close();

      for (int i = 0 ; i < doc.get(CoreAnnotations.MentionsAnnotation.class).size() ; i++) {
        CoreMap cm1 = doc.get(CoreAnnotations.MentionsAnnotation.class).get(i);
        CoreMap cm2 = readDoc.get(CoreAnnotations.MentionsAnnotation.class).get(i);
        diffCoreMaps(i,cm1,cm2);
      }

      sameAsRead(doc, readDoc);

      // Write 2
      ByteArrayOutputStream ks2 = new ByteArrayOutputStream();
      serializer.write(readDoc, ks2).close();

      // Read 2
      InputStream kis2 = new ByteArrayInputStream(ks2.toByteArray());
      Pair<Annotation, InputStream> pair = serializer.read(kis2);
      pair.second.close();
      Annotation readDoc2 = pair.first;
      kis2.close();

      sameAsRead(readDoc, readDoc2);
      sameAsRead(doc, readDoc2);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}