class DummyClass_88846 {
    @Test
    public void testDistributionFunction() {
        TreeMap<Integer, Double> distribution = hist1.computeDistributionFunction();

        int[] buckets = new int[distribution.size()];
        double[] sums = new double[distribution.size()];

        int ptr = 0;
        for(int bucket : distribution.keySet()) {
            sums[ptr] = distribution.get(bucket);
            buckets[ptr++] = bucket;
        }

        assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5}, buckets);
        assertArrayEquals(new double[] {4., 7., 9., 10., 11., 12.}, sums, 0.01);
    }

}