class DummyClass_77562 {
    @Test
        public String getMessage(@QueryParam("message") Optional<String> message) {
            return message.orElse("Default Message");
        }

}