class DummyClass_135780 {
    @Test
    public void testSetExceptionAfterResponse() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setResponse("theResponse");

        try {
            cr.setException(new RuntimeException("anException"));
            fail("expected IllegalState");
        } catch (IllegalStateException e) {

        }

        assertEquals("theResponse", v.get());
    }

}