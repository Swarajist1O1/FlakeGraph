class DummyClass_114081 {
    @Test
    public void joinValues_correctlyJoins() {
        Map<String, AttributeValue> values1 = new HashMap<>();
        values1.put("one", EnhancedAttributeValue.fromString("1").toAttributeValue());
        values1.put("two", EnhancedAttributeValue.fromString("2").toAttributeValue());
        Map<String, AttributeValue> values2 = new HashMap<>();
        values2.put("three", EnhancedAttributeValue.fromString("3").toAttributeValue());
        values2.put("four", EnhancedAttributeValue.fromString("4").toAttributeValue());

        Map<String, AttributeValue> result = Expression.joinValues(values1, values2);

        assertThat(result.size(), is(4));
        assertThat(result, hasEntry("one", EnhancedAttributeValue.fromString("1").toAttributeValue()));
        assertThat(result, hasEntry("two", EnhancedAttributeValue.fromString("2").toAttributeValue()));
        assertThat(result, hasEntry("three", EnhancedAttributeValue.fromString("3").toAttributeValue()));
        assertThat(result, hasEntry("four", EnhancedAttributeValue.fromString("4").toAttributeValue()));
    }

}