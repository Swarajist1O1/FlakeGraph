class DummyClass_97990 {
    @Test
    public void testObserveOnWithNewThreadScheduler() {
        final AtomicInteger count = new AtomicInteger();
        final int _multiple = 99;

        Observable.range(1, 100000).map(new Func1<Integer, Integer>() {

            @Override
            public Integer call(Integer t1) {
                return t1 * _multiple;
            }

}