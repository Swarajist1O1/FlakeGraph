class DummyClass_135782 {
    @Test
    public void testSetResponseDuplicate() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setResponse("theResponse");

        try {
            cr.setResponse("theResponse2");
            fail("expected IllegalState");
        } catch (IllegalStateException e) {

        }

        assertEquals("theResponse", v.get());
    }

}