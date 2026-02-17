class DummyClass_42982 {
	@Test
	public void testApiObjectDoc() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(TestObject.class);
		ApiObjectDoc childDoc = jsondocScanner.getApiObjectDocs(classes).iterator().next(); 
		Assert.assertEquals("test-object", childDoc.getName());
		Assert.assertEquals(14, childDoc.getFields().size());
		Assert.assertEquals("1.0", childDoc.getSupportedversions().getSince());
		Assert.assertEquals("2.12", childDoc.getSupportedversions().getUntil());
		Assert.assertEquals(ApiVisibility.PUBLIC, childDoc.getVisibility());
		Assert.assertEquals(ApiStage.PRE_ALPHA, childDoc.getStage());
		
		for (ApiObjectFieldDoc fieldDoc : childDoc.getFields()) {
			if(fieldDoc.getName().equals("wildcardParametrized")) {
				Assert.assertEquals("list", fieldDoc.getJsondocType().getType().get(0));
			}
			
			if(fieldDoc.getName().equals("unparametrizedList")) {
				Assert.assertEquals("list", fieldDoc.getJsondocType().getType().get(0));
			}
			
			if(fieldDoc.getName().equals("parametrizedList")) {
				Assert.assertEquals("list of string", fieldDoc.getJsondocType().getOneLineText());
			}
			
			if(fieldDoc.getName().equals("name")) {
				Assert.assertEquals("string", fieldDoc.getJsondocType().getType().get(0));
				Assert.assertEquals("name", fieldDoc.getName());
				Assert.assertEquals("true", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("age")) {
				Assert.assertEquals("integer", fieldDoc.getJsondocType().getType().get(0));
				Assert.assertEquals("age", fieldDoc.getName());
				Assert.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("avg")) {
				Assert.assertEquals("long", fieldDoc.getJsondocType().getType().get(0));
				Assert.assertEquals("avg", fieldDoc.getName());
				Assert.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("map")) {
				Assert.assertEquals("map", fieldDoc.getJsondocType().getType().get(0));
				Assert.assertEquals("string", fieldDoc.getJsondocType().getMapKey().getType().get(0));
				Assert.assertEquals("integer", fieldDoc.getJsondocType().getMapValue().getType().get(0));
			}
			
			if(fieldDoc.getName().equals("LongArray")) {
				Assert.assertEquals("array of long", fieldDoc.getJsondocType().getOneLineText());
				Assert.assertEquals("LongArray", fieldDoc.getName());
				Assert.assertEquals("false", fieldDoc.getRequired());
			}

			if(fieldDoc.getName().equals("longArray")) {
				Assert.assertEquals("array of long", fieldDoc.getJsondocType().getOneLineText());
				Assert.assertEquals("longArray", fieldDoc.getName());
				Assert.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("fooBar")) {
				Assert.assertEquals("string", fieldDoc.getJsondocType().getOneLineText());
				Assert.assertEquals("foo_bar", fieldDoc.getName());
				Assert.assertEquals("false", fieldDoc.getRequired());
			}
			
			if(fieldDoc.getName().equals("version")) {
				Assert.assertEquals("string", fieldDoc.getJsondocType().getOneLineText());
				Assert.assertEquals("1.0", fieldDoc.getSupportedversions().getSince());
				Assert.assertEquals("2.12", fieldDoc.getSupportedversions().getUntil());
			}
			
			if(fieldDoc.getName().equals("test-enum")) {
				Assert.assertEquals("test-enum", fieldDoc.getName());
				Assert.assertEquals(TestEnum.TESTENUM1.name(), fieldDoc.getAllowedvalues()[0]);
				Assert.assertEquals(TestEnum.TESTENUM2.name(), fieldDoc.getAllowedvalues()[1]);
				Assert.assertEquals(TestEnum.TESTENUM3.name(), fieldDoc.getAllowedvalues()[2]);
			}
			
			if(fieldDoc.getName().equals("test-enum-with-allowed-values")) {
				Assert.assertEquals("A", fieldDoc.getAllowedvalues()[0]);
				Assert.assertEquals("B", fieldDoc.getAllowedvalues()[1]);
				Assert.assertEquals("C", fieldDoc.getAllowedvalues()[2]);
			}

			if(fieldDoc.getName().equals("orderedProperty")) {
				Assert.assertEquals("orderedProperty", fieldDoc.getName());
				Assert.assertEquals(1, fieldDoc.getOrder().intValue());
			} else {
				Assert.assertEquals(Integer.MAX_VALUE, fieldDoc.getOrder().intValue());
			}

		}
	}

}