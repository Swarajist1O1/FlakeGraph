class DummyClass_91419 {
@TestLogging("org.elasticsearch.xpack.security.authz.store.NativePrivilegeStore:TRACE")
    public void setup() {
        requests = new ArrayList<>();
        listener = new AtomicReference<>();
        client = new NoOpClient(getTestName()) {
            @Override
            protected <Request extends ActionRequest,
                Response extends ActionResponse,
                RequestBuilder extends ActionRequestBuilder<Request, Response, RequestBuilder>>
            void doExecute(Action<Request, Response, RequestBuilder> action, Request request, ActionListener<Response> listener) {
                NativePrivilegeStoreTests.this.requests.add(request);
                NativePrivilegeStoreTests.this.listener.set(listener);
            }
        };
        final SecurityIndexManager securityIndex = mock(SecurityIndexManager.class);
        when(securityIndex.freeze()).thenReturn(securityIndex);
        when(securityIndex.indexExists()).thenReturn(true);
        when(securityIndex.isAvailable()).thenReturn(true);
        Mockito.doAnswer(invocationOnMock -> {
            assertThat(invocationOnMock.getArguments().length, equalTo(2));
            assertThat(invocationOnMock.getArguments()[1], instanceOf(Runnable.class));
            ((Runnable) invocationOnMock.getArguments()[1]).run();
            return null;
        }).when(securityIndex).prepareIndexIfNeededThenExecute(any(Consumer.class), any(Runnable.class));
        Mockito.doAnswer(invocationOnMock -> {
            assertThat(invocationOnMock.getArguments().length, equalTo(2));
            assertThat(invocationOnMock.getArguments()[1], instanceOf(Runnable.class));
            ((Runnable) invocationOnMock.getArguments()[1]).run();
            return null;
        }).when(securityIndex).checkIndexVersionThenExecute(any(Consumer.class), any(Runnable.class));
        store = new NativePrivilegeStore(Settings.EMPTY, client, securityIndex);
    }

}