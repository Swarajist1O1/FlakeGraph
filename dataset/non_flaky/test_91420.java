class DummyClass_91420 {
@TestLogging("org.elasticsearch.xpack.security.action.user.TransportHasPrivilegesAction:TRACE," +
    public void setup() {
        final Settings settings = Settings.builder().build();
        user = new User(randomAlphaOfLengthBetween(4, 12));
        final ThreadPool threadPool = mock(ThreadPool.class);
        final ThreadContext threadContext = new ThreadContext(Settings.EMPTY);
        final TransportService transportService = new TransportService(Settings.EMPTY, mock(Transport.class), null,
            TransportService.NOOP_TRANSPORT_INTERCEPTOR, x -> null, null, Collections.emptySet());

        final Authentication authentication = mock(Authentication.class);
        threadContext.putTransient(AuthenticationField.AUTHENTICATION_KEY, authentication);
        when(threadPool.getThreadContext()).thenReturn(threadContext);

        when(authentication.getUser()).thenReturn(user);

        AuthorizationService authorizationService = mock(AuthorizationService.class);
        Mockito.doAnswer(invocationOnMock -> {
            ActionListener<Role> listener = (ActionListener<Role>) invocationOnMock.getArguments()[1];
            listener.onResponse(role);
            return null;
        }).when(authorizationService).roles(eq(user), any(ActionListener.class));

        applicationPrivileges = new ArrayList<>();
        NativePrivilegeStore privilegeStore = mock(NativePrivilegeStore.class);
        Mockito.doAnswer(inv -> {
            assertThat(inv.getArguments(), arrayWithSize(3));
            ActionListener<List<ApplicationPrivilegeDescriptor>> listener
                = (ActionListener<List<ApplicationPrivilegeDescriptor>>) inv.getArguments()[2];
            logger.info("Privileges for ({}) are {}", Arrays.toString(inv.getArguments()), applicationPrivileges);
            listener.onResponse(applicationPrivileges);
            return null;
        }).when(privilegeStore).getPrivileges(any(Collection.class), any(Collection.class), any(ActionListener.class));

        action = new TransportHasPrivilegesAction(settings, threadPool, transportService, mock(ActionFilters.class),
            mock(IndexNameExpressionResolver.class), authorizationService, privilegeStore);
    }

}