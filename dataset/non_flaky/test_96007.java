class DummyClass_96007 {
  @Test
  public void testNERStanfordDependencies() throws Exception{
    String sentence = "John lives in Washington.";
    Properties props = new Properties();
    props.setProperty("annotators","tokenize, ssplit, pos, lemma, ner, parse");
    props.setProperty("parse.originalDependencies", "true");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation doc = new Annotation(sentence);
    pipeline.annotate(doc);
    CoreMap sent = doc.get(CoreAnnotations.SentencesAnnotation.class).get(0);
    SemanticGraph graph = sent.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
    graph.prettyPrint();
    String patStr = "({word:/lives/} >/prep_in/ {word:/\\QCalifornia\\E|\\QWashington\\E/} >nsubj {ner:PERSON})";
    SemgrexPattern pat = SemgrexPattern.compile(patStr);
    SemgrexMatcher mat = pat.matcher(graph, true);
    assertTrue(mat.find());
  }

}