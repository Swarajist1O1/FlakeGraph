class DummyClass_104095 {
	@Test
	public void stateChangedWorks() {
		ConfigClientWatch watch = new ConfigClientWatch(null);
		assertThat(watch.stateChanged(null, "1")).isTrue();
		assertThat(watch.stateChanged("1", "2")).isTrue();
		assertThat(watch.stateChanged("1", null)).isTrue();
		assertThat(watch.stateChanged("1", "1")).isFalse();
		watch.close();
	}

}