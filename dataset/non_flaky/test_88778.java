class DummyClass_88778 {
    @Test
    public void testServiceExposedAndCallbacksInvoked() throws Exception {
        assertNotNull(ignite);
        assertEquals("testGrid", ignite.name());

        TestOsgiFlags flags = (TestOsgiFlags) bundleCtx.getService(
            bundleCtx.getAllServiceReferences(TestOsgiFlags.class.getName(), null)[0]);

        assertNotNull(flags);
        assertEquals(Boolean.TRUE, flags.getOnBeforeStartInvoked());
        assertEquals(Boolean.TRUE, flags.getOnAfterStartInvoked());

        // The bundle is still not stopped, therefore these callbacks cannot be tested.
        assertNull(flags.getOnBeforeStopInvoked());
        assertNull(flags.getOnAfterStopInvoked());

        // No exceptions.
        assertNull(flags.getOnAfterStartThrowable());
        assertNull(flags.getOnAfterStopThrowable());
    }

}