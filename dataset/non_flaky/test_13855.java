class DummyClass_13855 {
    @Test(expected = MismatchingStoreIdException.class)
    public void makeSureServerStoreIdsMustMatch() throws Throwable
    {
        MadeUpServer server = builder.storeId( new StoreId( 10, 10, 10, 10 ) ).server();
        MadeUpClient client = builder.client();
        addToLifeAndStart( server, client );

        client.multiply( 1, 2 );
    }

}