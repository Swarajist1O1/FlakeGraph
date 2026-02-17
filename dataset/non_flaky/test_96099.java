class DummyClass_96099 {
  @Test
  public void testSave() throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    new ProtobufAnnotationSerializer().write(mkAnnotation(), os).close();
    String json = new String(os.toByteArray(), "UTF-8").trim();
    assertNotNull(json);
  }

}