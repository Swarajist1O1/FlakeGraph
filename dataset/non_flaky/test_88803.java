class DummyClass_88803 {
    @Test
    public void testFailover() throws Exception {
        final int CLUSTER_SIZE = 3;

        try (LocalIgniteCluster cluster = LocalIgniteCluster.start(CLUSTER_SIZE);
             IgniteClient client = Ignition.startClient(new ClientConfiguration()
                 .setAddresses(cluster.clientAddresses().toArray(new String[CLUSTER_SIZE]))
             )
        ) {
            final Random rnd = new Random();

            final ClientCache<Integer, String> cache = client.getOrCreateCache(
                new ClientCacheConfiguration().setName("testFailover").setCacheMode(CacheMode.REPLICATED)
            );

            // Simple operation failover: put/get
            assertOnUnstableCluster(cluster, () -> {
                Integer key = rnd.nextInt();
                String val = key.toString();

                cache.put(key, val);

                String cachedVal = cache.get(key);

                assertEquals(val, cachedVal);
            });

            // Composite operation failover: query
            Map<Integer, String> data = IntStream.rangeClosed(1, 1000).boxed()
                .collect(Collectors.toMap(i -> i, i -> String.format("String %s", i)));

            assertOnUnstableCluster(cluster, () -> {
                cache.putAll(data);

                Query<Cache.Entry<Integer, String>> qry =
                    new ScanQuery<Integer, String>().setPageSize(data.size() / 10);

                try (QueryCursor<Cache.Entry<Integer, String>> cur = cache.query(qry)) {
                    List<Cache.Entry<Integer, String>> res = cur.getAll();

                    assertEquals("Unexpected number of entries", data.size(), res.size());

                    Map<Integer, String> act = res.stream()
                        .collect(Collectors.toMap(Cache.Entry::getKey, Cache.Entry::getValue));

                    assertEquals("Unexpected entries", data, act);
                }
            });

            // Client fails if all nodes go down
            cluster.close();

            boolean igniteUnavailable = false;

            try {
                cache.put(1, "1");
            }
            catch (ClientConnectionException ex) {
                igniteUnavailable = true;

                Throwable[] suppressed = ex.getSuppressed();

                assertEquals(suppressed.length, CLUSTER_SIZE - 1);

                assertTrue(Stream.of(suppressed).allMatch(t -> t instanceof ClientConnectionException));
            }

            assertTrue(igniteUnavailable);
        }
    }

}