class DummyClass_88814 {
    @Test
    public void testClientFailsOnStart() {
        ClientConnectionException expEx = null;

        try (IgniteClient ignored = Ignition.startClient(getClientConfiguration())) {
            // No-op.
        }
        catch (ClientConnectionException connEx) {
            expEx = connEx;
        }
        catch (Exception ex) {
            fail(String.format(
                "%s expected but %s was received: %s",
                ClientConnectionException.class.getName(),
                ex.getClass().getName(),
                ex
            ));
        }

        assertNotNull(
            String.format("%s expected but no exception was received", ClientConnectionException.class.getName()),
            expEx
        );
    }

}