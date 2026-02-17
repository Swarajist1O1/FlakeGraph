class DummyClass_113955 {
	@Test
	public void testCas() {
		User user = new User("1", "Dave", "Wilson");
		userRepository.save(user).block();
		user.setVersion(user.getVersion() - 1);
		assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user).block());
		user.setVersion(0);
		userRepository.save(user).block();
		userRepository.delete(user).block();
	}

}