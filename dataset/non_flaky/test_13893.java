class DummyClass_13893 {
    @Test
    public void canGetBranchedStoreBean() throws Throwable
    {
        startCluster( 1 );
        BranchedStore bs = beans( cluster.getMaster() ).getBranchedStoreBean();
        assertNotNull( "could not get branched store bean", bs );
        assertEquals( "no branched stores for new db", 0,
                bs.getBranchedStores().length );
    }

}