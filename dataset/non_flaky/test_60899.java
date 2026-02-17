class DummyClass_60899 {
  @Test
  public void testDisableJavaScript()
  {
    final JavaScriptParseSpec parseSpec = new JavaScriptParseSpec(
        new TimestampSpec("timestamp", "auto", null),
        new DimensionsSpec(
            DimensionsSpec.getDefaultSchemas(
                ImmutableList.of(
                    "dim1",
                    "dim2"
                )
            ),
            null,
            null
        ),
        "func",
        new JavaScriptConfig(false)
    );
    ThriftInputRowParser parser = new ThriftInputRowParser(
        parseSpec,
        "example/book.jar",
        "org.apache.druid.data.input.thrift.Book"
    );

    expectedException.expect(CoreMatchers.instanceOf(IllegalStateException.class));
    expectedException.expectMessage("JavaScript is disabled");

    //noinspection ResultOfMethodCallIgnored (this method call will trigger the expected exception)
    parser.parseBatch(ByteBuffer.allocate(1)).get(0);
  }

}