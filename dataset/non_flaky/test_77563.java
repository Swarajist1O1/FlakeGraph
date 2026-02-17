class DummyClass_77563 {
    @Test
        public String getMessage(@HeaderParam("message") Optional<String> message) {
            return message.orElse("Default Message");
        }

}