class DummyClass_99798 {
//    @Test
//    public void getPreferredRemoteAddrUsesPrivateIpOverridesPreferredIp() throws UnknownHostException
//    {
//        MessagingService ms = MessagingService.instance();
//        InetAddressAndPort local = InetAddressAndPort.getByNameOverrideDefaults("127.0.0.4", 7000);
//        InetAddressAndPort remote = InetAddressAndPort.getByNameOverrideDefaults("127.0.0.105", 7000);
//        InetAddressAndPort privateIp = InetAddressAndPort.getByName("127.0.0.6");
//
//        OutboundConnectionSettings template = new OutboundConnectionSettings(remote)
//                                              .withConnectTo(privateIp)
//                                              .withAuthenticator(ALLOW_NOTHING_AUTHENTICATOR);
//
//        OutboundConnections pool = new OutboundConnections(template, new MockBackPressureStrategy(null).newState(remote));
//        ms.channelManagers.put(remote, pool);
//
//        InetAddressAndPort preferredIp = InetAddressAndPort.getByName("127.0.0.16");
//        SystemKeyspace.updatePreferredIP(remote, preferredIp);
//
//        Assert.assertEquals(privateIp, ms.getPreferredRemoteAddr(remote));
//    }

}