class DummyClass_88857 {
    @Test
    public void testBuildWithPredicate() {
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < 100; i++)
            data.put(i, i);

        LocalDatasetBuilder<Integer, Integer> builder = new LocalDatasetBuilder<>(data, (k, v) -> k % 2 == 0,10);

        LocalDataset<Serializable, TestPartitionData> dataset = buildDataset(builder);

        AtomicLong cnt = new AtomicLong();

        dataset.compute((partData, partIdx) -> {
            cnt.incrementAndGet();

            int[] arr = partData.data;

            assertEquals(5, arr.length);

            for (int i = 0; i < 5; i++)
                assertEquals((partIdx * 5 + i) * 2, arr[i]);
        });

        assertEquals(10, cnt.intValue());
    }

}