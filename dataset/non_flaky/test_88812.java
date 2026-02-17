class DummyClass_88812 {
    @Test
    public void testAtomicPutGet() throws Exception {
        try (Ignite ignored = Ignition.start(Config.getServerConfiguration());
             IgniteClient client = Ignition.startClient(getClientConfiguration())
        ) {
            ClientCache<Integer, String> cache = client.createCache("testRemoveReplace");

            assertNull(cache.getAndPut(1, "1"));
            assertEquals("1", cache.getAndPut(1, "1.1"));

            assertEquals("1.1", cache.getAndRemove(1));
            assertNull(cache.getAndRemove(1));

            assertTrue(cache.putIfAbsent(1, "1"));
            assertFalse(cache.putIfAbsent(1, "1.1"));

            assertEquals("1", cache.getAndReplace(1, "1.1"));
            assertEquals("1.1", cache.getAndReplace(1, "1"));
            assertNull(cache.getAndReplace(2, "2"));
        }
    }

}