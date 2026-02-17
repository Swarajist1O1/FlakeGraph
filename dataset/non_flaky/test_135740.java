class DummyClass_135740 {
    @Test
    public void testNested1() {
        HystrixProperty<String> a = Factory.asProperty("a");
        assertEquals("a", a.get());

        HystrixProperty<String> aWithDefault = Factory.asProperty(a, "b");
        assertEquals("a", aWithDefault.get());
    }

}