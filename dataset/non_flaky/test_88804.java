class DummyClass_88804 {
    @Test
    public void testUnmarshalSchemalessIgniteBinaries() throws Exception {
        int key = 1;
        Person val = new Person(key, "Joe");

        try (Ignite srv = Ignition.start(Config.getServerConfiguration())) {
            // Add an entry directly to the Ignite server. This stores a schema-less object in the cache and
            // does not register schema in the client's metadata cache.
            srv.cache(Config.DEFAULT_CACHE_NAME).put(key, val);

            try (IgniteClient client = Ignition.startClient(new ClientConfiguration().setAddresses(Config.SERVER))) {
                ClientCache<Integer, Person> cache = client.cache(Config.DEFAULT_CACHE_NAME);

                Person cachedVal = cache.get(key);

                assertEquals(val, cachedVal);
            }
        }
    }

}