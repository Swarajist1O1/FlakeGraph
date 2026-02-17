class DummyClass_13854 {
    @Test(expected = MismatchingStoreIdException.class)
    public void makeSureClientStoreIdsMustMatch() throws Throwable
    {
        MadeUpServer server = builder.server();
        MadeUpClient client = builder.storeId( new StoreId( 10, 10, 10, 10 ) ).client();
        addToLifeAndStart( server, client );

        client.multiply( 1, 2 );
    }

}