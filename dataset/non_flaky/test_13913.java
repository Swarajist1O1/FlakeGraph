class DummyClass_13913 {
    @Test
    public void canWriteLargestAtomAfterFillingBuffer() throws Exception
    {
        byte[] bytes = new byte[300];
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer( bytes );
        wrappedBuffer.resetWriterIndex();
        BlockLogBuffer buffer = new BlockLogBuffer( wrappedBuffer, new Monitors().newMonitor( ByteCounterMonitor.class ) );

        byte[] bytesValue = new byte[255];
        bytesValue[0] = 1;
        bytesValue[254] = -1;
        long longValue = 123456;
        buffer.put( bytesValue, bytesValue.length );
        buffer.putLong( longValue );
        buffer.close();

        ByteBuffer verificationBuffer = ByteBuffer.wrap( bytes );
        assertEquals( (byte) 0, verificationBuffer.get() );
        byte[] actualBytes = new byte[bytesValue.length];
        verificationBuffer.get( actualBytes );
        assertThat( actualBytes, new ArrayMatches<byte[]>( bytesValue ) );
        assertEquals( (byte) 8, verificationBuffer.get() );
        assertEquals( longValue, verificationBuffer.getLong() );
    }

}