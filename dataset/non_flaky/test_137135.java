class DummyClass_137135 {
	@Test
			public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
				return MergedAnnotationReadingVisitor.get(getClass().getClassLoader(),
						null, descriptor, visible,
						annotation -> MergedAnnotationMetadataVisitorTests.this.annotation = annotation);
			}

}