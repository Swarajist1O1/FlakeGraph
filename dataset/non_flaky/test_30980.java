class DummyClass_30980 {
    @Test
    public void shouldNotAccumulateSizeWithUndeclaredProperties() {

      // given
      newSchemaObject.wrap(bufferSerializedWithOldSchema);
      final int length = newSchemaObject.getLength();

      final var buffer = new UnsafeBuffer(ByteBuffer.allocate(100));
      newSchemaObject.write(buffer, 0);

      // when
      newSchemaObject.wrap(buffer);

      // then
      assertThat(newSchemaObject.getLength()).isEqualTo(length);
    }

}