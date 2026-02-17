class DummyClass_26845 {
    @Test
    public void testOwnerBindingReferences() throws Exception {
        final Name name = new CompositeName("test");
        final ServiceName serviceName = store.buildServiceName(name);
        final Object value = new Object();

        // ensure bind does not exists
        try {
            store.lookup(name);
            fail("Should have thrown name not found");
        } catch (NameNotFoundException expect) {
        }
        final RuntimeBindReleaseService.References duBindingReferences = (RuntimeBindReleaseService.References) container.getService(JndiNamingDependencyProcessor.serviceName(OWNER_FOO)).getValue();
        WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
        try {
            store.bind(name, value);
            // Foo's RuntimeBindReleaseService should now have a reference to the new bind
            assertTrue(duBindingReferences.contains(serviceName));

            store.rebind(name, value);
            // after rebind, Foo's RuntimeBindReleaseService should continue to have a reference to the bind
            assertTrue(duBindingReferences.contains(serviceName));

            store.unbind(name);
        } finally {
            WritableServiceBasedNamingStore.popOwner();
        }
    }

}