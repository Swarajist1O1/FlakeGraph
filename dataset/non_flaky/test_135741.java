class DummyClass_135741 {
    @Test
    public void testNested2() {
        HystrixProperty<String> nullValue = Factory.nullProperty();

        HystrixProperty<String> withDefault = Factory.asProperty(nullValue, "b");
        assertEquals("b", withDefault.get());
    }

}