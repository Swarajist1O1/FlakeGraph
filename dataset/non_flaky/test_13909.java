class DummyClass_13909 {
    @Test
    public void readSmallPortions() throws IOException
    {
        byte[] bytes = new byte[255];
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer( bytes );
        wrappedBuffer.resetWriterIndex();
        BlockLogBuffer buffer = new BlockLogBuffer( wrappedBuffer, new Monitors().newMonitor( ByteCounterMonitor.class ) );

        byte byteValue = 5;
        int intValue = 1234;
        long longValue = 574853;
        buffer.put( byteValue );
        buffer.putInt( intValue );
        buffer.putLong( longValue );
        buffer.close();

        ReadableByteChannel reader = new BlockLogReader( wrappedBuffer );
        ByteBuffer verificationBuffer = ByteBuffer.wrap( new byte[1] );
        reader.read( verificationBuffer );
        verificationBuffer.flip();
        assertEquals( byteValue, verificationBuffer.get() );
        verificationBuffer = ByteBuffer.wrap( new byte[4] );
        reader.read( verificationBuffer );
        verificationBuffer.flip();
        assertEquals( intValue, verificationBuffer.getInt() );
        verificationBuffer = ByteBuffer.wrap( new byte[8] );
        reader.read( verificationBuffer );
        verificationBuffer.flip();
        assertEquals( longValue, verificationBuffer.getLong() );
    }

}