class DummyClass_135781 {
    @Test
    public void testSetResponseAfterException() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setException(new RuntimeException("anException"));

        try {
            cr.setResponse("theResponse");
            fail("expected IllegalState");
        } catch (IllegalStateException e) {

        }

        try {
            v.get();
            fail("expected exception");
        } catch (ExecutionException e) {
            assertEquals("anException", e.getCause().getMessage());
        }
    }

}