class DummyClass_96011 {
  @Test
  public void runHeidelTimeSpanish() throws Exception {
    String text = "El lunes, algunas noticias cataclÃ­smicas sobre un lanzamiento de la Navidad pasada fueron liberadas.";

    Annotation ann = new Annotation(text);
    String date = "2017-07-07";
    ann.set(CoreAnnotations.DocDateAnnotation.class, date);

    String heideltimeEnv = System.getenv("HEIDELTIME_PATH");
    if (heideltimeEnv == null) {
      heideltimeEnv = DEFAULT_HEIDELTIME_LOCATION;
    }

    Properties defaultProps = new Properties();
    defaultProps.load(IOUtils.getInputStreamFromURLOrClasspathOrFileSystem("edu/stanford/nlp/pipeline/StanfordCoreNLP-spanish.properties"));

    Properties props = new Properties(defaultProps);
    props.setProperty("customAnnotatorClass.heideltime", "edu.stanford.nlp.time.HeidelTimeAnnotator");
    props.setProperty(HeidelTimeAnnotator.HEIDELTIME_PATH_PROPERTY, heideltimeEnv);
    props.setProperty(HeidelTimeAnnotator.HEIDELTIME_LANGUAGE_PROPERTY, "spanish");
    props.setProperty("annotators", "tokenize,ssplit,heideltime");

    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.annotate(ann);

    List<CoreMap> outputs = ann.get(TimeAnnotations.TimexAnnotations.class);
    Assert.assertEquals(1, outputs.size()); // Unfortunately, HeidelTime doesn't get Navidad :-(

    Assert.assertEquals("El lunes", outputs.get(0).get(TimeAnnotations.TimexAnnotation.class).text());
    Assert.assertEquals("2017-07-03", outputs.get(0).get(TimeAnnotations.TimexAnnotation.class).value());

    //Assert.assertEquals("Navidad", outputs.get(1).get(TimeAnnotations.TimexAnnotation.class).text());
    //Assert.assertEquals("2016-12-25", outputs.get(1).get(TimeAnnotations.TimexAnnotation.class).value());
  }

}