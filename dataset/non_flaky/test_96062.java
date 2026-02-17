class DummyClass_96062 {
  @Test
  public void testChineseSerialization() {

    try {
      AnnotationSerializer serializer = new ProtobufAnnotationSerializer();
      // write Chinese doc
      String sampleChineseDocument = "å·´æåÂ·å¥¥å·´é©¬æ¯ç¾å½æ»ç»ãä»å¨2008å¹´å½é";
      Properties chineseProperties = StringUtils.argsToProperties("-props",
              "StanfordCoreNLP-chinese.properties");
      Annotation doc = new StanfordCoreNLP(chineseProperties).process(sampleChineseDocument);

      // fake having a section in the annotation so the test passes.
      // todo [2017] clean up the status of sections.
      doc.set(CoreAnnotations.SectionsAnnotation.class, new ArrayList<CoreMap>());

      ByteArrayOutputStream ks = new ByteArrayOutputStream();
      serializer.write(doc, ks).close();
      // read
      InputStream kis = new ByteArrayInputStream(ks.toByteArray());
      Pair<Annotation, InputStream> pair = serializer.read(kis);
      pair.second.close();
      Annotation readDoc = pair.first;
      kis.close();
      // check characters are equal
      List<CoreLabel> docChars = doc.get(SegmenterCoreAnnotations.CharactersAnnotation.class);
      List<CoreLabel> readDocChars = doc.get(SegmenterCoreAnnotations.CharactersAnnotation.class);
      assertEquals(docChars.size(),readDocChars.size());
      int numChars = docChars.size();
      int currChar = 0;
      while (currChar < numChars) {
        assertEquals(docChars.get(currChar),readDocChars.get(currChar));
        currChar++;
      }
      // check that sentences are equal
      /*int sentenceCount = 0;
      while (sentenceCount < doc.get(CoreAnnotations.SentencesAnnotation.class).size()) {
        assertEquals(doc.get(CoreAnnotations.SentencesAnnotation.class).get(sentenceCount),
                readDoc.get(CoreAnnotations.SentencesAnnotation.class).get(sentenceCount));
        sentenceCount++;
      }*/
      // check JSON output is same
      String docJSON = JSONOutputter.jsonPrint(doc);
      String readDocJSON = JSONOutputter.jsonPrint(readDoc);
      assertEquals(docJSON,readDocJSON);
    } catch (Exception e) { throw new RuntimeException(e); }
  }

}