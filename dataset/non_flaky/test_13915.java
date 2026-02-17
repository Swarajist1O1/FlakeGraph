class DummyClass_13915 {
    @Test
    public void canReaderReallyLargeByteArray() throws Exception
    {
        byte[] bytes = new byte[650];
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer( bytes );
        wrappedBuffer.resetWriterIndex();
        BlockLogBuffer buffer = new BlockLogBuffer( wrappedBuffer, new Monitors().newMonitor( ByteCounterMonitor.class ) );

        byte[] bytesValue = new byte[600];
        bytesValue[1] = 1;
        bytesValue[99] = 2;
        bytesValue[199] = 3;
        bytesValue[299] = 4;
        bytesValue[399] = 5;
        bytesValue[499] = 6;
        bytesValue[599] = 7;
        buffer.put( bytesValue, bytesValue.length );
        buffer.close();

        byte[] actual;
        BlockLogReader reader = new BlockLogReader( wrappedBuffer );
        ByteBuffer verificationBuffer = ByteBuffer.wrap( new byte[1000] );
        reader.read( verificationBuffer );
        verificationBuffer.flip();
        actual = new byte[255];
        verificationBuffer.get( actual );
        assertThat( actual, new ArrayMatches<byte[]>( Arrays.copyOfRange( bytesValue, 0, 255 ) ) );
        actual = new byte[255];
        verificationBuffer.get( actual );
        assertThat( actual, new ArrayMatches<byte[]>( Arrays.copyOfRange( bytesValue, 255, 510 ) ) );
        actual = new byte[90];
        verificationBuffer.get( actual );
        assertThat( actual, new ArrayMatches<byte[]>( Arrays.copyOfRange( bytesValue, 510, 600 ) ) );
    }

}