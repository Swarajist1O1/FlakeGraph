class DummyClass_97974 {
    @Test
    public void testReduceWithInitialValue() {
        Observable<Integer> observable = Observable.from(1, 2, 3, 4);
        observable.reduce(50, new Func2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer t1, Integer t2) {
                return t1 + t2;
            }

}