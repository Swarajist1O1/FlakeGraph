class DummyClass_95656 {
    @Test
    public void featureShouldBeInactiveByDefault() {
        assertFalse(manager.isActive(MyFeatures.ONE));
    }

}