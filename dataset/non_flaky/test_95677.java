class DummyClass_95677 {
    @Test
    public void featureShouldBeActiveWithAnnotation()
    {
        assertTrue(MyFeatures.ONE.isActive());
        assertFalse(MyFeatures.TWO.isActive());
    }

}