class DummyClass_13843 {
    @Test
    public void shouldNotReuseBrokenInstances() throws Exception
    {
        ResourcePool<Something> pool = new ResourcePool<Something>( 5 )
        {
            @Override
            protected Something create()
            {
                return new Something();
            }

            @Override
            protected boolean isAlive( Something resource )
            {
                return !resource.closed;
            }
        };

        Something somethingFirst = pool.acquire();
        somethingFirst.doStuff();
        pool.release();

        Something something = pool.acquire();
        assertEquals( somethingFirst, something );
        something.doStuff();
        something.close();
        pool.release();

        Something somethingElse = pool.acquire();
        assertFalse( something == somethingElse );
        somethingElse.doStuff();
    }

}