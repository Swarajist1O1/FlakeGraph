class DummyClass_95676 {
    @Test
    public void featureShouldBeInactiveByDefault()
    {
        assertFalse(MyFeatures.ONE.isActive());
        assertFalse(MyFeatures.TWO.isActive());
    }

}