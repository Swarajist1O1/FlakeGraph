class DummyClass_20969 {
    @Test
    public void testExtentOfStorage() {

        GorillaStore gStore = new GorillaStore();

        HashMap<String, String> tags = new HashMap<>();
        tags.put("host", "localhost");

        long start = System.currentTimeMillis();
        long timestamp = start;

        for (int x = 1; x <= 100; x++) {

            System.out.println("adding value x:" + x);
            gStore.addValue(timestamp, 2.0);
            timestamp = timestamp + 1000;

            if (x % 10 == 0) {
                gStore.archiveCurrentCompressor();
            }
            if (x < 50) {
                continue;
            }

            System.out.println("fetching values x:" + x);
            long totalObservations = 0;

            List<WrappedGorillaDecompressor> decompressorList = gStore.getDecompressors(start, timestamp);
            Pair pair = null;
            for (WrappedGorillaDecompressor w : decompressorList) {
                while ((pair = w.readPair()) != null) {
                    totalObservations++;
                    // System.out.println(pair.getTimestamp() + " --> " +
                    // pair.getDoubleValue());
                }
            }

            Assert.assertEquals("Unexpected number of total observations", x, totalObservations);

        }

    }

}