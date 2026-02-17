class DummyClass_162561 {
  @Test
  public void shouldMapRequestFieldsOnly() {

    // given
    AwsSdkRequest awsSdkRequest = BatchWriteItem;
    MethodHandleFactory methodHandleFactory = new MethodHandleFactory();
    Serializer serializer = mock(Serializer.class);
    FieldMapper underTest = new FieldMapper(serializer, methodHandleFactory);
    Map<String, Collection<WriteRequest>> items = new HashMap();
    BatchWriteItemRequest sdkRequest = BatchWriteItemRequest.builder().requestItems(items).build();
    given(serializer.serialize(items)).willReturn("firstTable,secondTable");

    Span span = mock(Span.class);
    // when
    underTest.mapToAttributes(sdkRequest, awsSdkRequest, span);
    // then
    verify(span).setAttribute("aws.dynamodb.table_names", "firstTable,secondTable");
    verifyNoMoreInteractions(span);
  }

}