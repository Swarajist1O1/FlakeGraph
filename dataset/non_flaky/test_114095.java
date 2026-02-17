class DummyClass_114095 {
    @Test
    public void nullPartitionKey_shouldThrowException() {
        AttributeValue attributeValue = null;
        assertThatThrownBy(() ->  Key.builder().partitionValue(attributeValue).build())
         .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("partitionValue should not be null");

        assertThatThrownBy(() ->  Key.builder().partitionValue(AttributeValue.builder().nul(true).build()).build())
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("partitionValue should not be null");
    }

}