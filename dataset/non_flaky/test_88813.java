class DummyClass_88813 {
    @Test
    public void testRemoveReplace() throws Exception {
        try (Ignite ignored = Ignition.start(Config.getServerConfiguration());
             IgniteClient client = Ignition.startClient(getClientConfiguration())
        ) {
            ClientCache<Integer, String> cache = client.createCache("testRemoveReplace");

            Map<Integer, String> data = IntStream.rangeClosed(1, 100).boxed()
                .collect(Collectors.toMap(i -> i, Object::toString));

            cache.putAll(data);

            assertFalse(cache.replace(1, "2", "3"));
            assertEquals("1", cache.get(1));
            assertTrue(cache.replace(1, "1", "3"));
            assertEquals("3", cache.get(1));

            assertFalse(cache.replace(101, "101"));
            assertNull(cache.get(101));
            assertTrue(cache.replace(100, "101"));
            assertEquals("101", cache.get(100));

            assertFalse(cache.remove(101));
            assertTrue(cache.remove(100));
            assertNull(cache.get(100));

            assertFalse(cache.remove(99, "100"));
            assertEquals("99", cache.get(99));
            assertTrue(cache.remove(99, "99"));
            assertNull(cache.get(99));

            cache.put(101, "101");

            cache.removeAll(data.keySet());
            assertEquals(1, cache.size());
            assertEquals("101", cache.get(101));

            cache.removeAll();
            assertEquals(0, cache.size());
        }
    }

}