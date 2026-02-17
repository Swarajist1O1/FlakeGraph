class DummyClass_13911 {
    @Test
    public void onlyOneFullBlock() throws Exception
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

        ByteBuffer verificationBuffer = ByteBuffer.wrap( bytes );
        assertEquals( (byte) 255, verificationBuffer.get() );
        byte[] actualBytes = new byte[bytesValue.length];
        verificationBuffer.get( actualBytes );
        assertThat( actualBytes, new ArrayMatches<byte[]>( bytesValue ) );
    }

}