class DummyClass_135744 {
    @Test
    public void testNested5() {
        HystrixProperty<String> nullValue = Factory.nullProperty();
        HystrixProperty<String> a = Factory.asProperty(nullValue, null);

        @SuppressWarnings("unchecked")
        HystrixProperty<String> withDefault = Factory.asProperty(a, Factory.asProperty("b"));
        assertEquals("b", withDefault.get());
    }

}