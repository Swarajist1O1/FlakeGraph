class DummyClass_137086 {
	@Test
	public void isIndependentWhenNotIndependentReturnsFalse() {
		assertThat(get(TestNonStaticInnerClass.class).isIndependent()).isFalse();
	}

}