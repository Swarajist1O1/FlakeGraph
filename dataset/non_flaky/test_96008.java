class DummyClass_96008 {
  @Test
  public void testNERUniversalDependencies() throws Exception{
    String sentence = "John lives in Washington.";
    Properties props = new Properties();
    props.setProperty("annotators","tokenize, ssplit, pos, lemma, ner, parse");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    props.setProperty("parse.originalDependencies", "false");
    Annotation doc = new Annotation(sentence);
    pipeline.annotate(doc);
    CoreMap sent = doc.get(CoreAnnotations.SentencesAnnotation.class).get(0);
    SemanticGraph graph = sent.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
    graph.prettyPrint();
    String patStr = "({word:/lives/} >/obl:in/ {word:/\\QCalifornia\\E|\\QWashington\\E/} >nsubj {ner:PERSON})";
    SemgrexPattern pat = SemgrexPattern.compile(patStr);
    SemgrexMatcher mat = pat.matcher(graph, true);
    assertTrue(mat.find());
  }

}