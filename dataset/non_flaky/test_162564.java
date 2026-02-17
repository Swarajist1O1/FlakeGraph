class DummyClass_162564 {
  @Test
  public void shouldSerializeSdkPojo() {
    // given
    SdkPojo sdkPojo =
        ProvisionedThroughput.builder().readCapacityUnits(1L).writeCapacityUnits(2L).build();
    // when
    String serialized = new Serializer().serialize(sdkPojo);
    // then
    assertThat(serialized).isEqualTo("{\"ReadCapacityUnits\":1,\"WriteCapacityUnits\":2}");
  }

}