class DummyClass_88817 {
    @Test
    public void testFind() {
        BitSet[] matrix = provider.provide();

        int nodes = matrix.length;

        BitSet all = new BitSet(nodes);
        for (int i = 0; i < nodes; i++)
            all.set(i);

        FullyConnectedComponentSearcher searcher = new FullyConnectedComponentSearcher(matrix);

        BitSet res = searcher.findLargest(all);
        int size = res.cardinality();

        Assert.assertTrue("Actual = " + size + ", Expected = " + minAcceptableRes,
            size >= minAcceptableRes);
    }

}