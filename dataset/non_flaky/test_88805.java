class DummyClass_88805 {
    @Test
    public void testReadingSchemalessIgniteBinaries() throws Exception {
        int key = 1;
        Person val = new Person(key, "Joe");

        try (Ignite srv = Ignition.start(Config.getServerConfiguration())) {
            // Add an entry directly to the Ignite server. This stores a schema-less object in the cache and
            // does not register schema in the client's metadata cache.
            srv.cache(Config.DEFAULT_CACHE_NAME).put(key, val);

            try (IgniteClient client = Ignition.startClient(new ClientConfiguration().setAddresses(Config.SERVER))) {
                ClientCache<Integer, BinaryObject> cache = client.cache(Config.DEFAULT_CACHE_NAME).withKeepBinary();

                BinaryObject cachedVal = cache.get(key);

                assertEquals(val.getId(), cachedVal.field("id"));
                assertEquals(val.getName(), cachedVal.field("name"));
            }
        }
    }

}