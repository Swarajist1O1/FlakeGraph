class DummyClass_13878 {
    @Test
    public void assertSimilarity()
    {
        // Different machine ids
        assertFalse( new RequestContext( 1234, 1, 2, 0, 0 ).equals( new RequestContext( 1234, 2, 2, 0, 0 ) ) );

        // Different event identifiers
        assertFalse( new RequestContext( 1234, 1, 10, 0, 0 ).equals( new RequestContext( 1234, 1, 20, 0, 0 ) ) );

        // Different session ids
        assertFalse( new RequestContext( 1001, 1, 5, 0, 0 ).equals( new RequestContext( 1101, 1, 5, 0, 0 ) ) );

        // Same everything
        assertEquals( new RequestContext( 12345, 4, 9, 0, 0 ), new RequestContext( 12345, 4, 9, 0, 0 ) );
    }

}