class DummyClass_177986 {
    @Test
    public void testToolbarColor() {
        int color = Color.RED;
        Intent intent = new CustomTabsIntent.Builder().setToolbarColor(color).build().intent;
        assertTrue(intent.hasExtra(CustomTabsIntent.EXTRA_TOOLBAR_COLOR));
        assertEquals(color, intent.getIntExtra(CustomTabsIntent.EXTRA_TOOLBAR_COLOR, 0));
    }

}