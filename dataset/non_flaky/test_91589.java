class DummyClass_91589 {
    @Test
    public void basicTest() {
        List<Integer> a = Lists.newArrayList();
        a.add(100);
        a.add(2);
        a.add(92);
        a.add(1);
        a.add(0);
        PartialSorter.partialSort(a, Lists.newArrayList(1, 3, 4), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

}