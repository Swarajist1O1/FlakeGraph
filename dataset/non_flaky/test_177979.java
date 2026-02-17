class DummyClass_177979 {
    @Test
    public void testCircularInterpolationCrossZero() {
        assertEquals(270f, ColorUtils.circularInterpolate(270, 90, 0f), 0f);
        assertEquals(180f, ColorUtils.circularInterpolate(270, 90, 0.5f), 0f);
        assertEquals(90f, ColorUtils.circularInterpolate(270, 90, 1f), 0f);
    }

}