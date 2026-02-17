class DummyClass_88806 {
    @Test
    public void testBinaryObjectPutGet() throws Exception {
        int key = 1;

        try (Ignite ignored = Ignition.start(Config.getServerConfiguration())) {
            try (IgniteClient client =
                     Ignition.startClient(new ClientConfiguration().setAddresses(Config.SERVER))
            ) {
                IgniteBinary binary = client.binary();

                BinaryObject val = binary.builder("Person")
                    .setField("id", 1, int.class)
                    .setField("name", "Joe", String.class)
                    .build();

                ClientCache<Integer, BinaryObject> cache = client.cache(Config.DEFAULT_CACHE_NAME).withKeepBinary();

                cache.put(key, val);

                BinaryObject cachedVal =
                    client.cache(Config.DEFAULT_CACHE_NAME).<Integer, BinaryObject>withKeepBinary().get(key);

                assertBinaryObjectsEqual(val, cachedVal);
            }
        }
    }

}