class DummyClass_20965 {
    @Test
    public void testHDFSWrite() throws Exception {

        try {
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI("hdfs://localhost:8020"), configuration);
            GorillaStore store = new GorillaStore(fs, "mymetric", new timely.Configuration());

            long start = System.currentTimeMillis();
            WrappedGorillaCompressor originalCompressor = new WrappedGorillaCompressor(start);
            long t = start;

            for (int x = 1; x <= 10; x++) {
                originalCompressor.addValue(t, 10);
                t = t + 1000;
            }
            originalCompressor.close();

            store.writeCompressor("mymetric", originalCompressor);

            List<WrappedGorillaCompressor> archived = store.readCompressors(fs, new Path("/timely/cache/mymetric"));

            for (WrappedGorillaCompressor c : archived) {

                GorillaDecompressor d = new GorillaDecompressor(new LongArrayInput(c.getCompressorOutput()));
                LinkedList<Pair> q = new LinkedList<>();
                Pair p = null;
                while ((p = d.readPair()) != null) {
                    q.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}