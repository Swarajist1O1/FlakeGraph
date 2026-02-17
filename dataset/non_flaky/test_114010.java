class DummyClass_114010 {
    @Test
    public void findAnnotationMultipleActionNameNull() {
        StrutsTilesAnnotationProcessor annotationProcessor = new StrutsTilesAnnotationProcessor();
        TilesDefinition tilesDefinition = annotationProcessor.findAnnotation(new TilesTestActionMultipleAnnotations(), null);
        Assert.assertNotNull(tilesDefinition);
        Assert.assertEquals("def1", tilesDefinition.name());
    }

}