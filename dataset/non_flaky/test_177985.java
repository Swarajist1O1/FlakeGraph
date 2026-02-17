class DummyClass_177985 {
    @Test
    public void testBareboneCustomTabIntent() {
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        Intent intent = customTabsIntent.intent;
        assertNotNull(intent);
        assertNull(customTabsIntent.startAnimationBundle);

        assertEquals(Intent.ACTION_VIEW, intent.getAction());
        assertTrue(intent.hasExtra(CustomTabsIntent.EXTRA_SESSION));
        if (Build.VERSION.SDK_INT >= 18) {
            assertNull(intent.getExtras().getBinder(CustomTabsIntent.EXTRA_SESSION));
        }
        assertNull(intent.getComponent());
    }

}