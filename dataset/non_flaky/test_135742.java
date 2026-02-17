class DummyClass_135742 {
    @Test
    public void testNested3() {
        HystrixProperty<String> nullValue = Factory.nullProperty();
        HystrixProperty<String> a = Factory.asProperty(nullValue, "a");

        HystrixProperty<String> withDefault = Factory.asProperty(a, "b");
        assertEquals("a", withDefault.get());
    }

}