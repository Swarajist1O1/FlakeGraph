class DummyClass_96100 {
  @Test
  public void testSaveLarge() throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    new ProtobufAnnotationSerializer().write(mkLargeAnnotation(), os).close();
    String json = new String(os.toByteArray(), "UTF-8");
    assertNotNull(json);
  }

}