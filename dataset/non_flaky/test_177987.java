class DummyClass_177987 {
    @Test
    public void testToolbarColorIsNotAResource() {
        @ColorRes int colorId = android.R.color.background_dark;
        int color = InstrumentationRegistry.getContext().getResources().getColor(colorId);
        Intent intent = new CustomTabsIntent.Builder().setToolbarColor(colorId).build().intent;
        assertFalse("The color should not be a resource ID",
                color == intent.getIntExtra(CustomTabsIntent.EXTRA_TOOLBAR_COLOR, 0));
        intent = new CustomTabsIntent.Builder().setToolbarColor(color).build().intent;
        assertEquals(color, intent.getIntExtra(CustomTabsIntent.EXTRA_TOOLBAR_COLOR, 0));
    }

}