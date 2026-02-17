class DummyClass_21177 {
    @Test
    public void testPreferencesAreCreatedWithTheVisibilitySetInXml() {
        final Context context = InstrumentationRegistry.getTargetContext();
        final PreferenceManager manager = new PreferenceManager(context);
        final PreferenceScreen screen = manager.inflateFromResource(context,
                R.layout.test_visibility,
                null);

        // Preference without visibility set should be visible
        assertTrue(screen.getPreference(0).isVisible());
        // Preference with visibility set to true should be visible
        assertTrue(screen.getPreference(1).isVisible());
        // Preference with visibility set to false should not be invisible
        assertFalse(screen.getPreference(2).isVisible());
    }

}