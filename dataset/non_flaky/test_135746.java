class DummyClass_135746 {
    @Test
    public void testSeries2() {
        HystrixProperty<String> nullValue = Factory.nullProperty();
        HystrixProperty<String> a = Factory.asProperty(nullValue, null);

        @SuppressWarnings("unchecked")
        HystrixProperty<String> withDefault = Factory.asProperty(a, nullValue, Factory.asProperty("b"), nullValue, Factory.asProperty("c"));
        assertEquals("b", withDefault.get());
    }

}