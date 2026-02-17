class DummyClass_77506 {
    @Test
        public void filter(ContainerRequestContext requestContext) throws IOException {
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return new NullPrincipal();
                }

}