class DummyClass_322 {
@Test
void close() throws Exception {
    when(webSocketClient.getConnection()).thenReturn(webSocket);
    when(webSocketClient.isOpen()).thenReturn(true);
    webSocketConnection.close();
    Thread.sleep(10);
    verify(webSocket).close();
}
}