class DummyClass_13844 {
    @Test
    public void shouldTimeoutGracefully() throws InterruptedException
    {
        FakeClock clock = new FakeClock();

        ResourcePool.CheckStrategy timeStrategy = new ResourcePool.CheckStrategy.TimeoutCheckStrategy( 100, clock );

        while ( clock.currentTimeMillis() <= 100 )
        {
            assertFalse( timeStrategy.shouldCheck() );
            clock.forward( 10, TimeUnit.MILLISECONDS );
        }

        assertTrue( timeStrategy.shouldCheck() );

        clock.forward( 1, TimeUnit.MILLISECONDS );
        assertFalse( timeStrategy.shouldCheck() );
    }

}