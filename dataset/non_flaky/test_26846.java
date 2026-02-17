class DummyClass_26846 {
    @Test
    public void testMultipleOwnersBindingReferences() throws Exception {
        final Name name = new CompositeName("test");
        final ServiceName serviceName = store.buildServiceName(name);
        final Object value = new Object();

        // ensure bind does not exists
        try {
            store.lookup(name);
            fail("Should have thrown name not found");
        } catch (NameNotFoundException expect) {
        }
        // ensure the owners RuntimeBindReleaseService have no reference to the future bind
        final RuntimeBindReleaseService.References fooDuBindingReferences = (RuntimeBindReleaseService.References) container.getService(JndiNamingDependencyProcessor.serviceName(OWNER_FOO)).getValue();
        assertFalse(fooDuBindingReferences.contains(serviceName));
        final RuntimeBindReleaseService.References barDuBindingReferences = (RuntimeBindReleaseService.References) container.getService(JndiNamingDependencyProcessor.serviceName(OWNER_BAR)).getValue();
        assertFalse(barDuBindingReferences.contains(serviceName));

        WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
        try {
            store.bind(name, value);
            // Foo's RuntimeBindReleaseService should now have a reference to the new bind
            assertTrue(fooDuBindingReferences.contains(serviceName));
            // Bar's RuntimeBindReleaseService reference to the bind should not exist
            assertFalse(barDuBindingReferences.contains(serviceName));
        } finally {
            WritableServiceBasedNamingStore.popOwner();
        }

        WritableServiceBasedNamingStore.pushOwner(OWNER_BAR);
        try {
            store.rebind(name, value);
            // after rebind, Foo's RuntimeBindReleaseService reference to the bind should still exist
            assertTrue(fooDuBindingReferences.contains(serviceName));
            // after rebind, Bar's RuntimeBindReleaseService reference to the bind should now exist
            assertTrue(barDuBindingReferences.contains(serviceName));
        } finally {
            WritableServiceBasedNamingStore.popOwner();
        }

        WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
        try {
            store.unbind(name);
        } finally {
            WritableServiceBasedNamingStore.popOwner();
        }
    }

}