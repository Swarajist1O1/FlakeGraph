class DummyClass_135005 {
    @Test
    public void testMessagesReceivedInOrder() throws Exception {
        stacks.clear();
        EchoSocket.receivedEchos = new FutureResult<>();
        final ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create().build();
        final CountDownLatch done = new CountDownLatch(1);
        final AtomicReference<String> error = new AtomicReference<>();
        ContainerProvider.getWebSocketContainer()
                .connectToServer(new Endpoint() {
                                     @Override
                                     public void onOpen(final Session session, EndpointConfig endpointConfig) {

                                         try {
                                             RemoteEndpoint.Basic rem = session.getBasicRemote();
                                             List<String> messages = new ArrayList<>();
                                             for (int i = 0; i < MESSAGES; i++) {
                                                 byte[] data = new byte[2048];
                                                 (new Random()).nextBytes(data);
                                                 String crc = md5(data);
                                                 rem.sendBinary(ByteBuffer.wrap(data));
                                                 messages.add(crc);
                                             }

                                             List<String> received = EchoSocket.receivedEchos.getIoFuture().get();
                                             StringBuilder sb = new StringBuilder();
                                             boolean fail = false;
                                             for (int i = 0; i < messages.size(); i++) {
                                                 if (received.size() <= i) {
                                                     fail = true;
                                                     sb.append(i + ": should be " + messages.get(i) + " but is empty.");
                                                 } else {
                                                     if (!messages.get(i).equals(received.get(i))) {
                                                         fail = true;
                                                         sb.append(i + ": should be " + messages.get(i) + " but is " + received.get(i) + " (but found at " + received.indexOf(messages.get(i)) + ").");
                                                     }
                                                 }
                                             }
                                             if(fail) {
                                                 error.set(sb.toString());
                                             }
                                             done.countDown();

                                         } catch (Throwable t) {
                                             System.out.println(t);
                                         }
                                     }

}