class DummyClass_114013 {
    @Test
    public void buildDefiniton() {
        StrutsTilesAnnotationProcessor annotationProcessor = new StrutsTilesAnnotationProcessor();
        TilesDefinition tilesDefinition = annotationProcessor.findAnnotation(new TilesTestActionSingleAnnotation(), null);

        Definition definition = annotationProcessor.buildTilesDefinition("tileName", tilesDefinition);

        Assert.assertNotNull(definition);
        Assert.assertEquals("tileName", definition.getName());
        Assert.assertEquals("preparer", definition.getPreparer());
        Assert.assertEquals("base-definition", definition.getExtends());
        Attribute templateAttribute = definition.getTemplateAttribute();
        Assert.assertEquals("template", templateAttribute.getValue());
        Assert.assertEquals("type", templateAttribute.getRenderer());
        Assert.assertEquals("role", templateAttribute.getRole());
        Expression definitionExpressionObject = templateAttribute.getExpressionObject();
        Assert.assertEquals("templ*", definitionExpressionObject.getExpression());
        Assert.assertNull(definitionExpressionObject.getLanguage());

        Attribute putAttribute = definition.getAttribute("put-attr");
        Assert.assertNotNull(putAttribute);
        Assert.assertEquals("attr-val", putAttribute.getValue());
        Assert.assertEquals("attr-type", putAttribute.getRenderer());
        Assert.assertEquals("attr-role", putAttribute.getRole());
        Expression putAttrExpressionObject = putAttribute.getExpressionObject();
        Assert.assertEquals("expr", putAttrExpressionObject.getExpression());
        Assert.assertEquals("lang", putAttrExpressionObject.getLanguage());

        Attribute listAttribute = definition.getAttribute("list-name");
        Assert.assertEquals("list-role", listAttribute.getRole());
        List<Attribute> listValue = getListValue(listAttribute);
        Assert.assertEquals(2, listValue.size());

        Attribute addAttribute = listValue.get(0);
        Assert.assertEquals("list-attr-role", addAttribute.getRole());
        Assert.assertEquals("list-attr-val", addAttribute.getValue());
        Assert.assertEquals("list-attr-type", addAttribute.getRenderer());
        Expression addAttrExpressionObject = addAttribute.getExpressionObject();
        Assert.assertEquals("list-attr-expr", addAttrExpressionObject.getExpression());

        Attribute addListAttribute = listValue.get(1);
        Assert.assertEquals("list-list-attr-role", addListAttribute.getRole());
        List<Attribute> addListValue = getListValue(addListAttribute);
        Assert.assertEquals(1, addListValue.size());
        Assert.assertEquals("list-list-add-attr", addListValue.get(0).getValue());

        Set<String> cascadedAttributeNames = definition.getCascadedAttributeNames();
        Assert.assertEquals(2, cascadedAttributeNames.size());
        Assert.assertTrue(cascadedAttributeNames.contains("put-attr"));
        Assert.assertTrue(cascadedAttributeNames.contains("list-name"));
    }

}