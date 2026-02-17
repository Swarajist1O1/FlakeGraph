class DummyClass_30978 {
    @Test
    public void shouldResetObjectBeforeReadingValue() {
      // given
      final var property = new StringProperty("property", "default");
      final var unpackedObject = new UnpackedObject();

      unpackedObject.declareProperty(property);

      final var buffer = new UnsafeBuffer(ByteBuffer.allocate(100));

      unpackedObject.write(buffer, 0);

      final var spyUnpackedObject = spy(unpackedObject);

      // when
      spyUnpackedObject.wrap(buffer);

      // then
      final var orderOfInvocations = Mockito.inOrder(spyUnpackedObject);
      orderOfInvocations.verify(spyUnpackedObject).reset();
      orderOfInvocations.verify(spyUnpackedObject).read(Mockito.any());
    }

}