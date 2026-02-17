class DummyClass_95678 {
    @Test
    public void shouldActivateMultipleFeatures()
    {
        assertTrue(MyFeatures.ONE.isActive());
        assertTrue(MyFeatures.TWO.isActive());
    }

}