class DummyClass_97976 {
    @Test
    public void testOnSubscribeFails() {
        @SuppressWarnings("unchecked")
        Observer<String> observer = mock(Observer.class);
        final RuntimeException re = new RuntimeException("bad impl");
        Observable<String> o = Observable.create(new OnSubscribeFunc<String>() {

            @Override
            public Subscription onSubscribe(Observer<? super String> t1) {
                throw re;
            }

}