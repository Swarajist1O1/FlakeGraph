class DummyClass_26827 {
    @Test
    public void testNameImplies() {
        // check the compat <<ALL BINDINGS>> name
        assertEquals(new JndiPermission("<<ALL BINDINGS>>", "*"), new JndiPermission("-", "*"));

        // check the root - name
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("-", "*")));
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("", "*")));
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("foo", "*")));
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("/foo", "*")));
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("foo/", "*")));
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("foo/bar/baz/zap", "*")));
        assertTrue(new JndiPermission("-", "*").implies(new JndiPermission("java:foo", "*")));

        // check the non-root - name
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("/-", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("/", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("//", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("////", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("/foo", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("/foo", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("/foo/", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("/foo/bar/baz/zap", "*")));
        assertTrue(new JndiPermission("/-", "*").implies(new JndiPermission("java:/foo", "*")));

        assertTrue(new JndiPermission("foo/-", "*").implies(new JndiPermission("foo/-", "*")));
        assertTrue(new JndiPermission("foo/-", "*").implies(new JndiPermission("foo/foo", "*")));
        assertTrue(new JndiPermission("foo/-", "*").implies(new JndiPermission("foo/foo", "*")));
        assertTrue(new JndiPermission("foo/-", "*").implies(new JndiPermission("foo/foo/", "*")));
        assertTrue(new JndiPermission("foo/-", "*").implies(new JndiPermission("foo/foo/bar/baz/zap", "*")));
        assertTrue(new JndiPermission("foo/-", "*").implies(new JndiPermission("java:foo/foo", "*")));

        // check the * name
        assertTrue(new JndiPermission("*", "*").implies(new JndiPermission("", "*")));
        assertTrue(new JndiPermission("*", "*").implies(new JndiPermission("foo", "*")));
        assertFalse(new JndiPermission("*", "*").implies(new JndiPermission("foo/bar", "*")));
        assertFalse(new JndiPermission("*", "*").implies(new JndiPermission("foo/", "*")));
        assertFalse(new JndiPermission("*", "*").implies(new JndiPermission("/foo", "*")));
        assertTrue(new JndiPermission("*/*", "*").implies(new JndiPermission("/foo", "*")));
        assertTrue(new JndiPermission("/*", "*").implies(new JndiPermission("/foo", "*")));
        assertTrue(new JndiPermission("*/foo", "*").implies(new JndiPermission("/foo", "*")));

        // check java: support
        assertEquals(new JndiPermission("java:", "*"), new JndiPermission("", "*"));
        assertEquals(new JndiPermission("java:/", "*"), new JndiPermission("/", "*"));
        assertEquals(new JndiPermission("java:-", "*"), new JndiPermission("-", "*"));
        assertEquals(new JndiPermission("java:*", "*"), new JndiPermission("*", "*"));
    }

}