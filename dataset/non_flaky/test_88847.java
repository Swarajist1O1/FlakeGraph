class DummyClass_88847 {
    @Test
    public void testOfSum() {
        IgniteFunction<Double, Integer> bucketMap = x -> (int) (Math.ceil(x * 100) % 100);
        IgniteFunction<Double, Double> cntrMap = x -> Math.pow(x, 2);

        ObjectHistogram<Double> forAllHistogram = new ObjectHistogram<>(bucketMap, cntrMap);
        Random rnd = new Random();
        List<ObjectHistogram<Double>> partitions = new ArrayList<>();
        int cntOfPartitions = rnd.nextInt(100);
        int sizeOfDataset = rnd.nextInt(10000);
        for(int i = 0; i < cntOfPartitions; i++)
            partitions.add(new ObjectHistogram<>(bucketMap, cntrMap));

        for(int i = 0; i < sizeOfDataset; i++) {
            double objVal = rnd.nextDouble();
            forAllHistogram.addElement(objVal);
            partitions.get(rnd.nextInt(partitions.size())).addElement(objVal);
        }

        Optional<ObjectHistogram<Double>> leftSum = partitions.stream().reduce(ObjectHistogram::plus);
        Optional<ObjectHistogram<Double>> rightSum = partitions.stream().reduce((x,y) -> y.plus(x));
        assertTrue(leftSum.isPresent());
        assertTrue(rightSum.isPresent());
        assertTrue(forAllHistogram.isEqualTo(leftSum.get()));
        assertTrue(forAllHistogram.isEqualTo(rightSum.get()));
        assertTrue(leftSum.get().isEqualTo(rightSum.get()));
    }

}