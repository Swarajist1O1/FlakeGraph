class DummyClass_177977 {
    @Test
    public void testCircularInterpolationForwards() {
        assertEquals(0f, ColorUtils.circularInterpolate(0, 180, 0f), 0f);
        assertEquals(90f, ColorUtils.circularInterpolate(0, 180, 0.5f), 0f);
        assertEquals(180f, ColorUtils.circularInterpolate(0, 180, 1f), 0f);
    }

}