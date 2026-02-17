class DummyClass_135743 {
    @Test
    public void testNested4() {
        HystrixProperty<String> nullValue = Factory.nullProperty();
        HystrixProperty<String> a = Factory.asProperty(nullValue, null);

        HystrixProperty<String> withDefault = Factory.asProperty(a, "b");
        assertEquals("b", withDefault.get());
    }

}