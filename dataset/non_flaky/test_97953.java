class DummyClass_97953 {
    @Test
    public void testWindow() {
        final ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();
        Observable.from(1, 2, 3, 4, 5, 6)
                .window(3).map(new Func1<Observable<Integer>, List<Integer>>() {

                    @Override
                    public List<Integer> call(Observable<Integer> o) {
                        return o.toList().toBlockingObservable().single();
                    }

}