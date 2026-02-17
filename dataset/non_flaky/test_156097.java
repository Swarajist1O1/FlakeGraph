class DummyClass_156097 {
  @Test
  public void testWriterToUTF8Buffered1() {
    final String clazz = "org.apache.xml.serializer.WriterToUTF8Buffered";
    final String[] params = { "char[]", "int", "int" };
    runXalanTest(prepareTarget(methodSigFromComponents(clazz, "void", "write", params), clazz));
  }

}