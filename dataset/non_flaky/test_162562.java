class DummyClass_162562 {
  @Test
  public void shouldMapResponseFieldsOnly() {

    // given
    AwsSdkRequest awsSdkRequest = BatchWriteItem;
    MethodHandleFactory methodHandleFactory = new MethodHandleFactory();
    Serializer serializer = mock(Serializer.class);
    FieldMapper underTest = new FieldMapper(serializer, methodHandleFactory);
    Map<String, Collection<ItemCollectionMetrics>> items = new HashMap();
    BatchWriteItemResponse sdkResponse =
        BatchWriteItemResponse.builder()
            .consumedCapacity(ConsumedCapacity.builder().build())
            .itemCollectionMetrics(items)
            .build();
    given(serializer.serialize(sdkResponse.consumedCapacity())).willReturn("consumedCapacity");
    given(serializer.serialize(items)).willReturn("itemCollectionMetrics");

    Span span = mock(Span.class);
    // when
    underTest.mapToAttributes(sdkResponse, awsSdkRequest, span);
    // then
    verify(span).setAttribute("aws.dynamodb.consumed_capacity", "consumedCapacity");
    verify(span).setAttribute("aws.dynamodb.item_collection_metrics", "itemCollectionMetrics");
    verifyNoMoreInteractions(span);
  }

}