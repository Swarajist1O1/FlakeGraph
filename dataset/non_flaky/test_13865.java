class DummyClass_13865 {
    @Test
    public void serverContextVerificationCanThrowException() throws Throwable
    {
        final String failureMessage = "I'm failing";
        TxChecksumVerifier failingVerifier = new TxChecksumVerifier()
        {
            @Override
            public void assertMatch( long txId, long checksum )
            {
                throw new FailingException( failureMessage );
            }

}