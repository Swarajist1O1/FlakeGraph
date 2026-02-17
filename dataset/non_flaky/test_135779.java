class DummyClass_135779 {
    @Test
    public void testSetException() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setException(new RuntimeException("anException"));

        // fetch value
        try {
            v.get();
            fail("expected exception");
        } catch (ExecutionException e) {
            assertEquals("anException", e.getCause().getMessage());
        }
    }

}