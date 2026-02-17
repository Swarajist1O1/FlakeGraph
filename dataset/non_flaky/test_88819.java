class DummyClass_88819 {
    @Test
    public void testBackwardCompatibilityMode() throws IgniteCheckedException {
        IgniteConfiguration cfg = getConfiguration(16 * MB);

        AtomicInteger totalEvicted = new AtomicInteger();

        ReplacedPageWriter pageWriter = (FullPageId fullPageId, ByteBuffer byteBuf, int tag) -> {
            log.info("Evicting " + fullPageId);

            assert getSegment(fullPageId).writeLock().isHeldByCurrentThread();

            totalEvicted.incrementAndGet();
        };

        System.setProperty(IgniteSystemProperties.IGNITE_DELAYED_REPLACED_PAGE_WRITE, "false");
        int pageSize = 4096;
        PageMemoryImpl memory;

        try {
            memory = createPageMemory(cfg, pageWriter, pageSize);
        }
        finally {
            System.clearProperty(IgniteSystemProperties.IGNITE_DELAYED_REPLACED_PAGE_WRITE);
        }

        this.pageMemory = memory;

        long pagesTotal = cfg.getDataStorageConfiguration().getDefaultDataRegionConfiguration().getMaxSize() / pageSize;
        long markDirty = pagesTotal * 2 / 3;
        for (int i = 0; i < markDirty; i++) {
            long pageId = memory.allocatePage(1, 1, PageIdAllocator.FLAG_DATA);
            long ptr = memory.acquirePage(1, pageId);

            memory.releasePage(1, pageId, ptr);
        }

        GridMultiCollectionWrapper<FullPageId> ids = memory.beginCheckpoint();
        int cpPages = ids.size();
        log.info("Started CP with [" + cpPages + "] pages in it, created [" + markDirty + "] pages");

        for (int i = 0; i < cpPages; i++) {
            long pageId = memory.allocatePage(1, 1, PageIdAllocator.FLAG_DATA);
            long ptr = memory.acquirePage(1, pageId);
            memory.releasePage(1, pageId, ptr);
        }

        assert totalEvicted.get() > 0;

        memory.stop(true);
    }

}