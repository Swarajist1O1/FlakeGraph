class DummyClass_13873 {
    @Test
    public void shouldAdhereToTransactionObligations() throws Exception
    {
        // GIVEN
        int value = 15;
        long desiredObligation = 8;
        life.add( builder.server() );
        MadeUpClient client = life.add( builder.client() );
        life.start();
        Response<Integer> respone = client.informAboutTransactionObligations( value, desiredObligation );
        TransactionObligationVerifyingResponseHandler handler = new TransactionObligationVerifyingResponseHandler();

        // WHEN
        respone.accept( handler );
        int responseValue = respone.response();

        // THEN
        assertEquals( value, responseValue );
        assertEquals( desiredObligation, handler.obligationTxId );
    }

}