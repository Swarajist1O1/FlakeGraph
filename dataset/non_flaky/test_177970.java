class DummyClass_177970 {
    @Test
    public void testColorToHslLimits() {
        final float[] hsl = new float[3];

        for (TestEntry entry : sEntryList) {
            ColorUtils.colorToHSL(entry.rgb, hsl);

            assertTrue(hsl[0] >= 0f && hsl[0] <= 360f);
            assertTrue(hsl[1] >= 0f && hsl[1] <= 1f);
            assertTrue(hsl[2] >= 0f && hsl[2] <= 1f);
        }
    }

}