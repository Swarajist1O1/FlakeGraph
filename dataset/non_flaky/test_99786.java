class DummyClass_99786 {
    @Test
    public void testFailedInternodeAuth() throws Exception
    {
        MessagingService ms = MessagingService.instance();
        DatabaseDescriptor.setInternodeAuthenticator(ALLOW_NOTHING_AUTHENTICATOR);
        InetAddressAndPort address = InetAddressAndPort.getByName("127.0.0.250");

        //Should return null
        Message messageOut = Message.out(Verb.ECHO_REQ, NoPayload.noPayload);
        assertFalse(ms.isConnected(address, messageOut));

        //Should tolerate null
        ms.closeOutbound(address);
        ms.send(messageOut, address);
    }

}