class DummyClass_96101 {
  @Test
  public void testSaveSize() throws IOException {
    // Annotate
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ByteArrayOutputStream compressedImpl = new ByteArrayOutputStream();
    GZIPOutputStream compressed = new GZIPOutputStream(compressedImpl);
    new ProtobufAnnotationSerializer().write(mkLargeAnnotation(), os).close();
    new ProtobufAnnotationSerializer().write(mkLargeAnnotation(), compressed).close();
    byte[] uncompressedProto = os.toByteArray();
    byte[] compressedProto = compressedImpl.toByteArray();
    assertNotNull(uncompressedProto);
    assertNotNull(compressedProto);

    // Check size
    assertTrue("Length too long: " + compressedProto.length, compressedProto.length < 460000);
    assertTrue("Length too long: " + uncompressedProto.length, uncompressedProto.length < 2800000);
  }

}