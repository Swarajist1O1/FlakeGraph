class DummyClass_60897 {
  @Test
  public void testGetThriftClass() throws Exception
  {
    ThriftInputRowParser parser1 = new ThriftInputRowParser(
        parseSpec,
        "example/book.jar",
        "org.apache.druid.data.input.thrift.Book"
    );
    Assert.assertEquals("org.apache.druid.data.input.thrift.Book", parser1.getThriftClass().getName());

    ThriftInputRowParser parser2 = new ThriftInputRowParser(parseSpec, null, "org.apache.druid.data.input.thrift.Book");
    Assert.assertEquals("org.apache.druid.data.input.thrift.Book", parser2.getThriftClass().getName());
  }

}