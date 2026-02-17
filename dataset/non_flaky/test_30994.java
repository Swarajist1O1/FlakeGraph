class DummyClass_30994 {
  @Test
  public void shouldFailSerializationWithMissingRequiredValues() {
    // given
    final POJO pojo = new POJO();

    final UnsafeBuffer buf = new UnsafeBuffer(new byte[1024]);

    // then
    exception.expect(MsgpackPropertyException.class);
    exception.expectMessage(
        "Property 'enumProp' is invalid: Expected a value or default value to be set before writing, but has nothing");

    // when
    pojo.write(buf, 0);
  }

}