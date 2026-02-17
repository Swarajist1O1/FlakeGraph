class DummyClass_13904 {
    @Test(expected = MismatchingStoreIdException.class)
    public void newClientsShouldNotIgnoreStoreIdDifferences() throws Throwable
    {
        // Given
        MasterImpl.SPI masterImplSPI = MasterImplTest.mockedSpi( new StoreId( 1, 2, 3, 4 ) );
        when( masterImplSPI.getTransactionChecksum( anyLong() ) ).thenReturn( 5L );

        cleanupRule.add( newMasterServer( masterImplSPI ) );

        StoreId storeId = new StoreId( 5, 6, 7, 8 );
        MasterClient214 masterClient214 = cleanupRule.add( newMasterClient214( storeId ) );

        // When
        masterClient214.handshake( 1, storeId );
    }

}