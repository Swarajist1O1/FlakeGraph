class DummyClass_88845 {
    @Test
    public void testAddHist() {
        ObjectHistogram<Double> res = hist1.plus(hist2);
        testBuckets(res, new int[] {0, 1, 2, 3, 4, 5, 6}, new int[] {10, 8, 2, 1, 1, 2, 1});
    }

}