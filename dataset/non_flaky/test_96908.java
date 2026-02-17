class DummyClass_96908 {
  @Test
  public void testCycleGeneration() throws ParseException, IOException {
    final ClassLoader cl = Thread.currentThread().getContextClassLoader();
    Idl idl = new Idl(cl.getResourceAsStream("input/cycle.avdl"),
            "UTF-8");
    Protocol protocol = idl.CompilationUnit();
    String json = protocol.toString();
    LOG.info(json);

    SpecificCompiler compiler = new SpecificCompiler(protocol);
    compiler.setStringType(GenericData.StringType.String);
    File output = new File("./target");
    compiler.compileToDestination(null, output);

    Map<String, Schema> schemas = new HashMap<>();
    for (Schema schema : protocol.getTypes()) {
      final String name = schema.getName();
      schemas.put(name, schema);
    }

    GenericRecordBuilder rb2 = new GenericRecordBuilder(schemas.get("SampleNode"));
    rb2.set("count", 10);
    rb2.set("subNodes", Collections.EMPTY_LIST);
    GenericData.Record node = rb2.build();

    GenericRecordBuilder mb = new GenericRecordBuilder(schemas.get("Method"));
    mb.set("declaringClass", "Test");
    mb.set("methodName", "test");
    GenericData.Record method = mb.build();

    GenericRecordBuilder spb = new GenericRecordBuilder(schemas.get("SamplePair"));
    spb.set("method", method);
    spb.set("node", node);
    GenericData.Record sp = spb.build();

    GenericRecordBuilder rb = new GenericRecordBuilder(schemas.get("SampleNode"));
    rb.set("count", 10);
    rb.set("subNodes", Arrays.asList(sp));
    GenericData.Record record = rb.build();

    serDeserRecord(record);

  }

}