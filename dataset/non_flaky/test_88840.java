class DummyClass_88840 {
    @Test
    public void testRandomOperations() throws Exception {
        int cnt = CACHE_ID_RANGE * PAGE_ID_RANGE;

        long mem = FullPageIdTable.requiredMemory(cnt);

        UnsafeMemoryProvider prov = new UnsafeMemoryProvider(log);

        prov.initialize(new long[] {mem});

        DirectMemoryRegion region = prov.nextRegion();

        try {
            long seed = U.currentTimeMillis();

            info("Seed: " + seed + "L; //");

            Random rnd = new Random(seed);

            LoadedPagesMap tbl = new FullPageIdTable(region.address(), region.size(), true);

            Map<FullPageId, Long> check = new HashMap<>();

            for (int i = 0; i < 10_000; i++) {
                int cacheId = rnd.nextInt(CACHE_ID_RANGE) + 1;
                int pageId = rnd.nextInt(PAGE_ID_RANGE);

                FullPageId fullId = new FullPageId(pageId, cacheId);

                boolean put = rnd.nextInt(3) != -1;

                if (put) {
                    long val = rnd.nextLong();

                    tbl.put(cacheId, pageId, val, 0);
                    check.put(fullId, val);
                }
                else {
                    tbl.remove(cacheId, pageId);
                    check.remove(fullId);
                }

                verifyLinear(tbl, check);

                if (i > 0 && i % 1000 == 0)
                    info("Done: " + i);
            }
        }
        finally {
            prov.shutdown(true);
        }
    }

}