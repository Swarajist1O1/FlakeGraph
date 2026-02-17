class DummyClass_13872 {
    @Test
    public void shouldStreamBackTransactions() throws Exception
    {
        // GIVEN
        int value = 11, txCount = 3;
        life.add( builder.server() );
        MadeUpClient client = life.add( builder.client() );
        life.start();
        Response<Integer> respone = client.streamBackTransactions( value, txCount );
        TransactionStreamVerifyingResponseHandler handler = new TransactionStreamVerifyingResponseHandler( txCount );

        // WHEN
        respone.accept( handler );
        int responseValue = respone.response();

        // THEN
        assertEquals( value, responseValue );
        assertEquals( txCount, handler.expectedTxId );
    }

}