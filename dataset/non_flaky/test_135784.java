class DummyClass_135784 {
    @Test(expected = CancellationException.class)
    public void testSetExceptionAfterUnsubscribe() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> f = o.toBlocking().toFuture();

        // cancel/unsubscribe
        f.cancel(true);

        try {
            cr.setException(new RuntimeException("anException"));
        } catch (IllegalStateException e) {
            fail("this should have done nothing as it was unsubscribed already");
        }

        // expect CancellationException after cancelling
        f.get();
    }

}