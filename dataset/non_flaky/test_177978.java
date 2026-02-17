class DummyClass_177978 {
    @Test
    public void testCircularInterpolationBackwards() {
        assertEquals(180f, ColorUtils.circularInterpolate(180, 0, 0f), 0f);
        assertEquals(90f, ColorUtils.circularInterpolate(180, 0, 0.5f), 0f);
        assertEquals(0f, ColorUtils.circularInterpolate(180, 0, 1f), 0f);
    }

}