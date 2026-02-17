class DummyClass_135778 {
    @Test
    public void testSetNullResponseSuccess() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setResponse(null);

        // fetch value
        assertEquals(null, v.get());
    }

}