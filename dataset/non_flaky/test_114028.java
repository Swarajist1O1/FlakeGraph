class DummyClass_114028 {
            // @Test(expected = JSONException.class)
    public void testSMDDisabledSMD() throws Exception {
        // request
        setRequestContent("smd-3.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        JSONUtil jsonUtil = new JSONUtil();
        jsonUtil.setWriter(new DefaultJSONWriter());
        interceptor.setJsonUtil(jsonUtil);
        SMDActionTest1 action = new SMDActionTest1();

        this.invocation.setAction(action);

        // SMD was not enabled so invocation must happen
        try {
            interceptor.intercept(this.invocation);
        } catch (JSONException e) {
            fail("Should have not thrown an exception");
        }

    }

}