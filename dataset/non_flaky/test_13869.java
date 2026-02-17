class DummyClass_13869 {
    @Test
    public void clientShouldUseHandlersToHandleComExceptions()
    {
        // Given
        final String comExceptionMessage = "The ComException";

        MadeUpCommunicationInterface communication = mock( MadeUpCommunicationInterface.class, new Answer<Response<?>>()
        {
            @Override
            public Response<?> answer( InvocationOnMock _ ) throws ComException
            {
                throw new ComException( comExceptionMessage );
            }

}