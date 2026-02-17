class DummyClass_30995 {
  @Test
  public void shouldFailLengthEstimationWithMissingRequiredValues() {
    // given
    final POJO pojo = new POJO();

    // then
    exception.expect(MsgpackPropertyException.class);
    exception.expectMessage(
        "Property 'enumProp' is invalid: Expected a value or default value to be specified, but has nothing");

    // when
    pojo.getLength();
  }

}