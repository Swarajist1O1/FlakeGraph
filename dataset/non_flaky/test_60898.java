class DummyClass_60898 {
  @Test
  public void testParse() throws Exception
  {
    ThriftInputRowParser parser = new ThriftInputRowParser(
        parseSpec,
        "example/book.jar",
        "org.apache.druid.data.input.thrift.Book"
    );
    Book book = new Book().setDate("2016-08-29").setPrice(19.9).setTitle("title")
                          .setAuthor(new Author().setFirstName("first").setLastName("last"));

    TSerializer serializer;
    byte[] bytes;

    // 1. compact
    serializer = new TSerializer(new TCompactProtocol.Factory());
    bytes = serializer.serialize(book);
    serializationAndTest(parser, bytes);

    // 2. binary + base64
    serializer = new TSerializer(new TBinaryProtocol.Factory());
    serializationAndTest(parser, StringUtils.encodeBase64(serializer.serialize(book)));

    // 3. json
    serializer = new TSerializer(new TJSONProtocol.Factory());
    bytes = serializer.serialize(book);
    serializationAndTest(parser, bytes);
  }

}