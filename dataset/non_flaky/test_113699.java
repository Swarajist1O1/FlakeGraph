class DummyClass_113699 {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("demo.client.android", appContext.getPackageName());
    }

}