class DummyClass_99787 {
//    @Test
//    public void reconnectWithNewIp() throws Exception
//    {
//        InetAddressAndPort publicIp = InetAddressAndPort.getByName("127.0.0.2");
//        InetAddressAndPort privateIp = InetAddressAndPort.getByName("127.0.0.3");
//
//        // reset the preferred IP value, for good test hygene
//        SystemKeyspace.updatePreferredIP(publicIp, publicIp);
//
//        // create pool/conn with public addr
//        Assert.assertEquals(publicIp, messagingService.getCurrentEndpoint(publicIp));
//        messagingService.maybeReconnectWithNewIp(publicIp, privateIp).await(1L, TimeUnit.SECONDS);
//        Assert.assertEquals(privateIp, messagingService.getCurrentEndpoint(publicIp));
//
//        messagingService.closeOutbound(publicIp);
//
//        // recreate the pool/conn, and make sure the preferred ip addr is used
//        Assert.assertEquals(privateIp, messagingService.getCurrentEndpoint(publicIp));
//    }

}