class DummyClass_77555 {
    @Test
        public Response toResponse(JerseyViolationException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.TEXT_PLAIN)
                .entity("Invalid data")
                .build();
        }

}