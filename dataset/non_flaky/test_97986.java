class DummyClass_97986 {
    @Test
    public void testTakeWithErrorInObserver() {
        final AtomicInteger count = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<Throwable>();
        Observable.from("1", "2", "three", "4").take(3).subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

}