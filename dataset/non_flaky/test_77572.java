class DummyClass_77572 {
    @Test
        public String getMessage(@HeaderParam("message") Optional<String> message) {
            return message.or("Default Message");
        }

}