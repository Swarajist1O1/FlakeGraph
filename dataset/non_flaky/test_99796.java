class DummyClass_99796 {
//    @Test
//    public void getPreferredRemoteAddrUsesPrivateIp() throws UnknownHostException
//    {
//        MessagingService ms = MessagingService.instance();
//        InetAddressAndPort remote = InetAddressAndPort.getByNameOverrideDefaults("127.0.0.151", 7000);
//        InetAddressAndPort privateIp = InetAddressAndPort.getByName("127.0.0.6");
//
//        OutboundConnectionSettings template = new OutboundConnectionSettings(remote)
//                                              .withConnectTo(privateIp)
//                                              .withAuthenticator(ALLOW_NOTHING_AUTHENTICATOR);
//        OutboundConnections pool = new OutboundConnections(template, new MockBackPressureStrategy(null).newState(remote));
//        ms.channelManagers.put(remote, pool);
//
//        Assert.assertEquals(privateIp, ms.getPreferredRemoteAddr(remote));
//    }

}