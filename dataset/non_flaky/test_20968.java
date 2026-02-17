class DummyClass_20968 {
    @Test
    public void testOne() {

        GorillaStore gStore = new GorillaStore();

        long now = System.currentTimeMillis();
        gStore.addValue(now += 100, 1.123);
        gStore.addValue(now += 100, 2.314);
        gStore.addValue(now += 100, 3.856);
        gStore.addValue(now += 100, 4.7678);
        gStore.addValue(now += 100, 5.8966);
        gStore.addValue(now += 100, 6.0976);
        gStore.addValue(now += 100, 1.2345);

        List<WrappedGorillaDecompressor> decompressorList = gStore.getDecompressors(0, Long.MAX_VALUE);
        Pair pair = null;
        for (WrappedGorillaDecompressor w : decompressorList) {
            while ((pair = w.readPair()) != null) {
                System.out.println(pair.getTimestamp() + " --> " + pair.getDoubleValue());
            }
        }

        System.out.println("---------------");

        gStore.addValue(now += 100, 2.3456);
        gStore.addValue(now += 100, 3.4567);

        decompressorList = gStore.getDecompressors(0, Long.MAX_VALUE);
        pair = null;
        for (WrappedGorillaDecompressor w : decompressorList) {
            while ((pair = w.readPair()) != null) {
                System.out.println(pair.getTimestamp() + " --> " + pair.getDoubleValue());
            }
        }
    }

}