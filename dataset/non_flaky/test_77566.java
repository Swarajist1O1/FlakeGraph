class DummyClass_77566 {
    @Test
        public String getMessage(@FormParam("message") Optional<String> message) {
            return message.orElse("Default Message");
        }

}