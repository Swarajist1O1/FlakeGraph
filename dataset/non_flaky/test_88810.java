class DummyClass_88810 {
    @Test
    public void testPutGet() throws Exception {
        // Existing cache, primitive key and object value
        try (Ignite ignored = Ignition.start(Config.getServerConfiguration());
             IgniteClient client = Ignition.startClient(getClientConfiguration())
        ) {
            ClientCache<Integer, Person> cache = client.getOrCreateCache(Config.DEFAULT_CACHE_NAME);

            Integer key = 1;
            Person val = new Person(key, "Joe");

            cache.put(key, val);

            assertTrue(cache.containsKey(key));

            Person cachedVal = cache.get(key);

            assertEquals(val, cachedVal);
        }

        // Non-existing cache, object key and primitive value
        try (Ignite ignored = Ignition.start(Config.getServerConfiguration());
             IgniteClient client = Ignition.startClient(getClientConfiguration())
        ) {
            ClientCache<Person, Integer> cache = client.getOrCreateCache("testPutGet");

            Integer val = 1;

            Person key = new Person(val, "Joe");

            cache.put(key, val);

            Integer cachedVal = cache.get(key);

            assertEquals(val, cachedVal);
        }

        // Object key and Object value
        try (Ignite ignored = Ignition.start(Config.getServerConfiguration());
             IgniteClient client = Ignition.startClient(getClientConfiguration())
        ) {
            ClientCache<Person, Person> cache = client.getOrCreateCache("testPutGet");

            Person key = new Person(1, "Joe Key");

            Person val = new Person(1, "Joe Value");

            cache.put(key, val);

            Person cachedVal = cache.get(key);

            assertEquals(val, cachedVal);
        }
    }

}