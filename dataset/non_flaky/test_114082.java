class DummyClass_114082 {
    @Test
    public void joinValues_conflictingKey() {
        Map<String, AttributeValue> values1 = new HashMap<>();
        values1.put("one", EnhancedAttributeValue.fromString("1").toAttributeValue());
        values1.put("two", EnhancedAttributeValue.fromString("2").toAttributeValue());
        Map<String, AttributeValue> values2 = new HashMap<>();
        values2.put("three", EnhancedAttributeValue.fromString("3").toAttributeValue());
        values2.put("two", EnhancedAttributeValue.fromString("4").toAttributeValue());

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("two");
        Expression.joinValues(values1, values2);
    }

}