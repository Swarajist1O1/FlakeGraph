class DummyClass_135785 {
    @Test
    public void testUnsubscribeAfterSetResponse() throws InterruptedException, ExecutionException {
        CollapsedRequestSubject<String, String> cr = new CollapsedRequestSubject<String, String>("hello");
        Observable<String> o = cr.toObservable();
        Future<String> v = o.toBlocking().toFuture();

        cr.setResponse("theResponse");

        // unsubscribe after the value is sent
        v.cancel(true);

        // still get value as it was set before canceling
        assertEquals("theResponse", v.get());
    }

}