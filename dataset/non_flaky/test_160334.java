class DummyClass_160334 {
  @Test
    public void messageReceived(final Serializable msg, final INode from) {
      synchronized (lock) {
        messages.add(msg);
        senders.add(from);
        lock.notifyAll();
      }
    }

}