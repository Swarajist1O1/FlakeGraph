class DummyClass_114012 {
    @Test
    public void findAnnotationMultipleActionNotFound() {
        StrutsTilesAnnotationProcessor annotationProcessor = new StrutsTilesAnnotationProcessor();
        TilesDefinition tilesDefinition = annotationProcessor.findAnnotation(new TilesTestActionMultipleAnnotations(), "def3");
        Assert.assertNull(tilesDefinition);
    }

}