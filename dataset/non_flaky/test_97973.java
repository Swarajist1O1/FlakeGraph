class DummyClass_97973 {
    @Test
    public void testReduce() {
        Observable<Integer> observable = Observable.from(1, 2, 3, 4);
        observable.reduce(new Func2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer t1, Integer t2) {
                return t1 + t2;
            }

}