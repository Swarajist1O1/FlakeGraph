class DummyClass_96036 {
  @Test
  public void testTokenSequenceMatcherPosNNP() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "[ { tag:\"NNP\" } ]+");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Mellitus", m.group());

    p = TokenSequencePattern.compile( "[ { tag:\"NNP\" } ] [ /is|was/ ] []*? [ { tag:\"NNP\" } ]+ ");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Mellitus was the first Bishop", m.group());

    TokenSequencePattern nnpPattern = TokenSequencePattern.compile( "[ { tag:\"NNP\" } ]" );
    Env env = TokenSequencePattern.getNewEnv();
    env.bind("$NNP", nnpPattern);
    p = TokenSequencePattern.compile(env, " $NNP [ /is|was/ ] []*? $NNP+ [ \"of\" ] $NNP+ ");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Mellitus was the first Bishop of London", m.group());

    p = TokenSequencePattern.compile(env, " ($NNP) /is|was/ []*? ($NNP)+ \"of\" ($NNP)+ ");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("Mellitus was the first Bishop of London", m.group());
    assertEquals("Mellitus", m.group(1));
    assertEquals("Bishop", m.group(2));
    assertEquals("London", m.group(3));


    nnpPattern = TokenSequencePattern.compile( " ( [ { tag:\"NNP\" } ] )" );
    env.bind("$NNP", nnpPattern);
    p = TokenSequencePattern.compile(env, " $NNP /is|was/ []*? $NNP+ \"of\" $NNP+ ");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("Mellitus was the first Bishop of London", m.group());
    assertEquals("Mellitus", m.group(1));
    assertEquals("Bishop", m.group(2));
    assertEquals("London", m.group(3));


    // Same as above but without extra "{}"
    nnpPattern = TokenSequencePattern.compile( " ( [ tag:\"NNP\" ] )" );
    env.bind("$NNP", nnpPattern);
    p = TokenSequencePattern.compile(env, " $NNP /is|was/ []*? $NNP+ \"of\" $NNP+ ");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("Mellitus was the first Bishop of London", m.group());
    assertEquals("Mellitus", m.group(1));
    assertEquals("Bishop", m.group(2));
    assertEquals("London", m.group(3));

    // Same as above but using "pos"
    nnpPattern = TokenSequencePattern.compile( " ( [ pos:\"NNP\" ] )" );
    env.bind("$NNP", nnpPattern);
    p = TokenSequencePattern.compile(env, " $NNP /is|was/ []*? $NNP+ \"of\" $NNP+ ");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("Mellitus was the first Bishop of London", m.group());
    assertEquals("Mellitus", m.group(1));
    assertEquals("Bishop", m.group(2));
    assertEquals("London", m.group(3));
  }

}