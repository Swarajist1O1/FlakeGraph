class DummyClass_98210 {
    @Test
    public void testException() {

        String wspuri = "https://example.org/foo/";
        IdURICache cache = new IdURICache(wspuri);
        String test;

        // port number
        test = "https://example.org:443/foo/x";
        try {
            cache.add(test, null);
            fail("should throw");
        } catch (IllegalArgumentException ex) {
            assertEquals("Workspace mismatch: '" + test + "' not under workspace '" + wspuri
                    + "' (position 18: '{https://example.org}:443/foo/x', expected: '/foo/')", ex.getMessage());
        }

        // protocol
        test = "http://example.org/foo/x";
        try {
            cache.add(test, null);
            fail("should throw");
        } catch (IllegalArgumentException ex) {
            assertEquals("Workspace mismatch: '" + test + "' not under workspace '" + wspuri
                    + "' (position 3: '{http}://example.org/foo/x', expected: 's://example.org/foo/')", ex.getMessage());
        }

        // hostname
        test = "https://example.com/foo/x";
        try {
            cache.add(test, null);
            fail("should throw");
        } catch (IllegalArgumentException ex) {
            assertEquals("Workspace mismatch: '" + test + "' not under workspace '" + wspuri
                    + "' (position 15: '{https://example.}com/foo/x', expected: 'org/foo/')", ex.getMessage());
        }

        // root path
        test = "https://example.org/bar/x";
        try {
            cache.add(test, null);
            fail("should throw");
        } catch (IllegalArgumentException ex) {
            assertEquals("Workspace mismatch: '" + test + "' not under workspace '" + wspuri
                    + "' (position 19: '{https://example.org/}bar/x', expected: 'foo/')", ex.getMessage());
        }

        // too short
        test = "https://example.org/fo/x";
        try {
            cache.add(test, null);
            fail("should throw");
        } catch (IllegalArgumentException ex) {
            assertEquals("Workspace mismatch: '" + test + "' not under workspace '" + wspuri
                    + "' (position 21: '{https://example.org/fo}/x', expected: 'o/')", ex.getMessage());
        }

        // way too short
        test = "https://x.org/foo/x";
        try {
            cache.add(test, null);
            fail("should throw");
        } catch (IllegalArgumentException ex) {
            assertEquals("Workspace mismatch: '" + test + "' not under workspace '" + wspuri
                    + "' (position 7: '{https://}x.org/foo/x', expected: 'example.org/foo/')", ex.getMessage());
        }
    }

}