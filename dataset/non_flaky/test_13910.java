class DummyClass_13910 {
    @Test
    public void readOnlyOneNonFullBlock() throws IOException
    {
        byte[] bytes = new byte[255];
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer( bytes );
        wrappedBuffer.resetWriterIndex();
        BlockLogBuffer buffer = new BlockLogBuffer( wrappedBuffer, new Monitors().newMonitor( ByteCounterMonitor.class ) );

        byte byteValue = 5;
        int intValue = 1234;
        long longValue = 574853;
        float floatValue = 304985.5f;
        double doubleValue = 48493.22d;
        final byte[] bytesValue = new byte[] { 1, 5, 2, 6, 3 };
        buffer.put( byteValue );
        buffer.putInt( intValue );
        buffer.putLong( longValue );
        buffer.putFloat( floatValue );
        buffer.putDouble( doubleValue );
        buffer.put( bytesValue, bytesValue.length );
        buffer.close();

        ReadableByteChannel reader = new BlockLogReader( wrappedBuffer );
        ByteBuffer verificationBuffer = ByteBuffer.wrap( new byte[1000] );
        reader.read( verificationBuffer );
        verificationBuffer.flip();
        assertEquals( byteValue, verificationBuffer.get() );
        assertEquals( intValue, verificationBuffer.getInt() );
        assertEquals( longValue, verificationBuffer.getLong() );
        assertEquals( floatValue, verificationBuffer.getFloat(), 0.0 );
        assertEquals( doubleValue, verificationBuffer.getDouble(), 0.0 );
        byte[] actualBytes = new byte[bytesValue.length];
        verificationBuffer.get( actualBytes );
        assertThat( actualBytes, new ArrayMatches<byte[]>( bytesValue ) );
    }

}