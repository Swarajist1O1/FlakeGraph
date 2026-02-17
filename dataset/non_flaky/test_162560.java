class DummyClass_162560 {
  @Test
  public void shouldMapNestedField() {

    // given
    AwsSdkRequest awsSdkRequest = UpdateTable;
    MethodHandleFactory methodHandleFactory = new MethodHandleFactory();
    Serializer serializer = mock(Serializer.class);
    FieldMapper underTest = new FieldMapper(serializer, methodHandleFactory);
    UpdateTableRequest sdkRequest =
        UpdateTableRequest.builder()
            .provisionedThroughput(
                ProvisionedThroughput.builder()
                    .readCapacityUnits(55L)
                    .writeCapacityUnits(77L)
                    .build())
            .build();
    given(serializer.serialize(55L)).willReturn("55");
    given(serializer.serialize(77L)).willReturn("77");

    Span span = mock(Span.class);
    // when
    underTest.mapToAttributes(sdkRequest, awsSdkRequest, span);
    // then
    verify(span).setAttribute("aws.dynamodb.provisioned_throughput.read_capacity_units", "55");
    verify(span).setAttribute("aws.dynamodb.provisioned_throughput.write_capacity_units", "77");
    verifyNoMoreInteractions(span);
  }

}