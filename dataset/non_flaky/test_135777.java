class DummyClass_135777 {
    @Test
    public void testSetResponseSuccess() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setResponse("theResponse");

        // fetch value
        assertEquals("theResponse", v.get());
    }

}