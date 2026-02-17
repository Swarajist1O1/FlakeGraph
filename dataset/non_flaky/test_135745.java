class DummyClass_135745 {
    @Test
    public void testSeries1() {
        HystrixProperty<String> nullValue = Factory.nullProperty();
        HystrixProperty<String> a = Factory.asProperty(nullValue, null);

        @SuppressWarnings("unchecked")
        HystrixProperty<String> withDefault = Factory.asProperty(a, nullValue, nullValue, Factory.asProperty("b"));
        assertEquals("b", withDefault.get());
    }

}