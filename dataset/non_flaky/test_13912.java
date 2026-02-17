class DummyClass_13912 {
    @Test
    public void readOnlyOneFullBlock() throws Exception
    {
        byte[] bytes = new byte[256];
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer( bytes );
        wrappedBuffer.resetWriterIndex();
        BlockLogBuffer buffer = new BlockLogBuffer( wrappedBuffer, new Monitors().newMonitor( ByteCounterMonitor.class ) );

        byte[] bytesValue = new byte[255];
        bytesValue[0] = 1;
        bytesValue[254] = -1;
        buffer.put( bytesValue, bytesValue.length );
        buffer.close();

        ReadableByteChannel reader = new BlockLogReader( wrappedBuffer );
        ByteBuffer verificationBuffer = ByteBuffer.wrap( new byte[1000] );
        reader.read( verificationBuffer );
        verificationBuffer.flip();
        byte[] actualBytes = new byte[bytesValue.length];
        verificationBuffer.get( actualBytes );
        assertThat( actualBytes, new ArrayMatches<byte[]>( bytesValue ) );
    }

}