class DummyClass_137074 {
	@Test
		public ResolvableType getResolvableType() {
			if (this.type == null) {
				return null;
			}
			return ResolvableType.forClassWithGenerics(getClass(), this.type);
		}

}