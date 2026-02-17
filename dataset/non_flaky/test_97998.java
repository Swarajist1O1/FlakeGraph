class DummyClass_97998 {
    @Test
    public void reduceInts() {
        Observable<Integer> o = Observable.from(1, 2, 3);
        int value = o.reduce(new Func2<Integer, Integer, Integer>() {

            @Override
            public Integer call(Integer t1, Integer t2) {
                return t1 + t2;
            }

}